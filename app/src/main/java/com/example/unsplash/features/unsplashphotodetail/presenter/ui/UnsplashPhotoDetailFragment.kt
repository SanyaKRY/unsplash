package com.example.unsplash.features.unsplashphotodetail.presenter.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentUnsplashPhotoDetailBinding
import com.example.unsplash.features.unsplashphotodetail.presenter.mapper.UiToDetailUiMapper
import com.example.unsplash.features.unsplashphotodetail.presenter.model.UnsplashPhotoDetailUi
import com.example.unsplash.features.unsplashphotodetail.presenter.vm.UnsplashPhotoDetailViewModel
import com.example.unsplash.core.datatype.Result
import com.example.unsplash.core.datatype.ResultType
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UnsplashPhotoDetailFragment : Fragment() {

    private val viewModel: UnsplashPhotoDetailViewModel by viewModel() {
        parametersOf(UiToDetailUiMapper.map(args.unsplashPhoto))
    }

    private var _binding: FragmentUnsplashPhotoDetailBinding? = null
    private val binding get() = _binding!!

    private val args: UnsplashPhotoDetailFragmentArgs by navArgs()

    private lateinit var unsplashPhoto: UnsplashPhotoDetailUi

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsplashPhotoDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        observerLiveData()
        bindViews()
        setTransition()
        setInserDeleteButtonImageView()

        return view
    }

    private fun setInserDeleteButtonImageView() {
        binding.insertDeleteImageView.setOnClickListener {
            unsplashPhoto.isSaved = unsplashPhoto.isSaved?.not()
            if (unsplashPhoto.isSaved!!) {
                viewModel.insertUnsplashPhoto(unsplashPhoto)
            } else {
                viewModel.deleteUnsplashPhoto(unsplashPhoto)
            }
            animateFavoriteIcon(unsplashPhoto.isSaved!!)
        }
    }

    // animation
    private fun animateFavoriteIcon(isFavoriteBeer: Boolean) {
        val viewPropertyAnimator = binding.insertDeleteImageView.animate()

        viewPropertyAnimator
            .scaleX(0.5f)
            .scaleY(0.5f)
            .setDuration(250)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    populateFavoriteIconView(isFavoriteBeer)
                    restartFavoriteIconSize(viewPropertyAnimator)
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                    binding.insertDeleteImageView.isClickable = false
                }
            })
    }

    // animation
    private fun populateFavoriteIconView(isFavorite: Boolean) {
        getFavoriteIcon(isFavorite)?.let {
            binding.insertDeleteImageView.setImageResource(it)
        }

    }

    // animation
    private fun getFavoriteIcon(isFavorite: Boolean): Int? {
        return if (isFavorite) {
            R.drawable.ic_star_full
        } else {
            R.drawable.ic_star_border
        }
    }

    // animation
    private fun restartFavoriteIconSize(viewPropertyAnimator: ViewPropertyAnimator) {
        viewPropertyAnimator
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(250)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    viewPropertyAnimator.cancel()
                    binding.insertDeleteImageView.isClickable = true
                }
            })
    }

    // transition
    private fun setTransition() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition()
        binding.unsplashPhotoImageView.apply {
            transitionName = unsplashPhoto.urlsRegular
            startEnterTransitionAfterLoadingImage(unsplashPhoto.urlsRegular, this)
        }
        binding.unsplashPhotoId.apply {
            transitionName = unsplashPhoto.unsplashPhotoId
        }
    }

    // transition
    private fun startEnterTransitionAfterLoadingImage(imageAddress: String, imageView: ImageView) {
        Glide.with(this)
            .load(imageAddress)
            .dontAnimate()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(imageView)
    }

    private fun observerLiveData() {
        viewModel.isSavedUnsplashPhotoLiveData.observe(
            viewLifecycleOwner,
            Observer(::isSavedUnsplashPhoto)
        )
    }

    private fun isSavedUnsplashPhoto(result: Result<Boolean>) {
        when (result.resultType) {
            ResultType.LOADING -> {
                binding.insertDeleteImageView.isVisible = false
            }
            ResultType.SUCCESS -> {
                binding.insertDeleteImageView.isVisible = true
                unsplashPhoto.isSaved = result.data
                populateFavoriteIconView(result.data!!)
            }
        }
    }

    private fun bindViews() {
        val unsplashPhotoDetailUi = UiToDetailUiMapper.map(args.unsplashPhoto)
        unsplashPhoto = unsplashPhotoDetailUi
        binding.apply {
            unsplashPhotoId.text = unsplashPhotoDetailUi.unsplashPhotoId
            loadImage(unsplashPhotoImageView, unsplashPhotoDetailUi.urlsRegular)
        }
    }

    private fun loadImage(imageView: AppCompatImageView, imageUri: String) {
        Glide.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}