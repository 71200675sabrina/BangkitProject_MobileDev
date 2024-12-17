package com.example.storyapp

import com.example.storyapp.remoteDao.StoryListItem

object DataDummy {
    fun generateDummyStoryItem(): List<StoryListItem>{
        return (0..100).map { i -> StoryListItem(
            i.toString(),
            "photoUrl + $i",
            "createdAt  $i",
            "name $i",
            "description $i",
            i.toDouble(),
            i.toDouble(),
        ) }
    }
}