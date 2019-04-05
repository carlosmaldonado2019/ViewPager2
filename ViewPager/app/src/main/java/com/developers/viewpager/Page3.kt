package com.developers.viewpager


import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.MediaController

import android.widget.TextView
import android.widget.VideoView
import kotlinx.android.synthetic.main.fragment_page3.*


class Page3 : Fragment() {

    lateinit var video: VideoView
    private val VIDEO_SAMPLE = "movie"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater!!.inflate(R.layout.fragment_page3, container, false)
        video = v.findViewById(R.id.videoView)
        val controller = MediaController(context)

        controller.setMediaPlayer(video)
        video.setMediaController(controller)
        return v
    }

    override fun onStart() {
        super.onStart()
        InitializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    fun releasePlayer() {
        video.stopPlayback()
    }

    fun InitializePlayer() {
        val videoUri = getMedia(VIDEO_SAMPLE)
        video.setVideoURI(videoUri)
        video.start()

    }

    private fun getMedia(mediaName: String): Uri {
        return Uri.parse("android.resource://com.developers.viewpager" +
                "/raw/" + mediaName)
    }


}