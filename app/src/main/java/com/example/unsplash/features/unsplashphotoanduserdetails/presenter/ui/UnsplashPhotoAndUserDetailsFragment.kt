package com.example.unsplash.features.unsplashphotoanduserdetails.presenter.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentUnsplashPhotoAndUserDetailsBinding
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.mapper.UiToUnsplashPhotoAndUserUiMapper
import com.example.unsplash.features.unsplashphotoanduserdetails.presenter.model.UnsplashPhotoAndUserDetailsUi
import com.example.unsplash.features.unsplashphotodetail.presenter.ui.UnsplashPhotoDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnsplashPhotoAndUserDetailsFragment : Fragment() {

    private var _binding: FragmentUnsplashPhotoAndUserDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: UnsplashPhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnsplashPhotoAndUserDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        args.apply {
            val unsplashPhoto: UnsplashPhotoAndUserDetailsUi = UiToUnsplashPhotoAndUserUiMapper.map(unsplashPhoto.unsplashPhotoDetailsBundleModel)

            binding.unsplashPhotoId.text = setTextView(binding.unsplashPhotoId.text, unsplashPhoto.id)
            binding.unsplashPhotoLikes.text = setTextView(binding.unsplashPhotoLikes.text, unsplashPhoto.likes.likes.toString())
            binding.userUsername.text = setTextView(binding.userUsername.text, unsplashPhoto.user.username)

            binding.userLinksHtml.text = setClickableLink(binding.userLinksHtml.text, unsplashPhoto.user.links.html)
            binding.userLinksHtml.movementMethod = LinkMovementMethod.getInstance()

            loadImage(binding.unsplashPhotoImageView, unsplashPhoto.urlsRegular)
        }

        return view
    }

    private fun setClickableLink(textTemplate: CharSequence, unsplashPhotoDetail: String): SpannableStringBuilder {
        val ss = SpannableStringBuilder(textTemplate)
        val boldSpan = StyleSpan(Typeface.BOLD)
        val colorSpan = ForegroundColorSpan(Color.RED)
        ss.setSpan(boldSpan, 0, textTemplate.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(colorSpan, 0, textTemplate.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        ss.append(" ").append("link")

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Log.d("PetProject", "url: ${unsplashPhotoDetail}")
                val url = unsplashPhotoDetail
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }
        }
        ss.setSpan(clickableSpan, textTemplate.length + 1, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return ss
    }

    private fun setTextView(textTemplate: CharSequence, unsplashPhotoDetail: String): SpannableStringBuilder {
        val ss = SpannableStringBuilder(textTemplate)
        val boldSpan = StyleSpan(Typeface.BOLD)
        val colorSpan = ForegroundColorSpan(Color.RED)
        ss.setSpan(boldSpan, 0, textTemplate.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(colorSpan, 0, textTemplate.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ss.append(" ").append(unsplashPhotoDetail)
    }

    private fun loadImage(imageView: AppCompatImageView, imageUri: String) {
        Glide.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .override(400, 600)
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}