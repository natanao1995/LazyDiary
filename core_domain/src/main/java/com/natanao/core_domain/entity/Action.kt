package com.natanao.core_domain.entity

/*
sealed class Action(
    open val id: Int,
    open val group: Group?,
    open val title: String,
    open val textIcon: String
) {
    data class UniqueAction(
        override val id: Int,
        override val group: Group?,
        override val title: String,
        override val textIcon: String
    ) : Action(id, group, title, textIcon)

    data class RepeatableAction(
        override val id: Int,
        override val group: Group?,
        override val title: String,
        override val textIcon: String,
        val parentId: Int
    ) : Action(id, group, title, textIcon)
}*/

sealed class Action(
    open val id: Int,
    open val title: String,
    open val textIcon: String
) {
    data class UniqueAction(
        override val id: Int,
        override val title: String,
        override val textIcon: String
    ) : Action(id, title, textIcon)

    data class RepeatableAction(
        override val id: Int,
        override val title: String,
        override val textIcon: String,
        val parentId: Int
    ) : Action(id, title, textIcon)
}
