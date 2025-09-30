package com.oynee.portfolio.adapter.persistence.mapper

import com.oynee.portfolio.adapter.persistence.entity.BookEntity
import com.oynee.portfolio.domain.Book
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BookEntityMapper {

    fun toBook(bookEntity: BookEntity): Book

    fun toBookEntity(book: Book): BookEntity

}