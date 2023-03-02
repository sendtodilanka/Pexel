/*
 * Designed and developed by 2022 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeboxlk.pexel.data.model.mapper

import com.codeboxlk.pexel.data.model.PexelPhoto
import com.codeboxlk.pexel.data.model.PexelPhotoEntity
import retrofit2.http.Query

object PexelPhotoEntityMapper : EntityMapper<List<PexelPhoto>, List<PexelPhotoEntity>> {

    override fun asEntity(domain: List<PexelPhoto>, page: Int, query: String): List<PexelPhotoEntity> {
        return domain.map { pexelPhoto ->
            PexelPhotoEntity(
                id = pexelPhoto.id,
                page = page,
                query = query,
                title = pexelPhoto.title,
                width = pexelPhoto.width,
                height = pexelPhoto.height,
                url = pexelPhoto.url,
                src = pexelPhoto.src,
                avgColor = pexelPhoto.avgColor,
                photographer = pexelPhoto.photographer,
                photographerId = pexelPhoto.photographerId,
                photographerUrl = pexelPhoto.photographerUrl
            )
        }
    }

    override fun asDomain(entity: List<PexelPhotoEntity>): List<PexelPhoto> {
        return entity.map { pexelPhotoEntity ->
            PexelPhoto(
                id = pexelPhotoEntity.id,
                title = pexelPhotoEntity.title,
                width = pexelPhotoEntity.width,
                height = pexelPhotoEntity.height,
                url = pexelPhotoEntity.url,
                src = pexelPhotoEntity.src,
                avgColor = pexelPhotoEntity.avgColor,
                photographer = pexelPhotoEntity.photographer,
                photographerId = pexelPhotoEntity.photographerId,
                photographerUrl = pexelPhotoEntity.photographerUrl
            )
        }
    }
}

fun List<PexelPhoto>.asEntity(page: Int, query: String): List<PexelPhotoEntity> {
    return PexelPhotoEntityMapper.asEntity(this, page, query)
}

fun List<PexelPhotoEntity>.asDomain(): List<PexelPhoto> {
    return PexelPhotoEntityMapper.asDomain(this)
}
