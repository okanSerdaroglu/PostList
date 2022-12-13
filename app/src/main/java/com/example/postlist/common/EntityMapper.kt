package com.example.postlist.common

interface EntityMapper<Entity : Any?, ProjectModel> {
    fun mapFromEntity(entityModel: Entity): ProjectModel
}