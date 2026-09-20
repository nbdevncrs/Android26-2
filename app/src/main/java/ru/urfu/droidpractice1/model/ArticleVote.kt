package ru.urfu.droidpractice1.model

enum class ArticleVote {
    NONE, LIKE, DISLIKE;

    fun toggled(selection: ArticleVote): ArticleVote =
        if (this == selection) NONE else selection

    companion object {
        fun fromName(value: String?): ArticleVote =
            values().firstOrNull { it.name == value } ?: NONE
    }
}
