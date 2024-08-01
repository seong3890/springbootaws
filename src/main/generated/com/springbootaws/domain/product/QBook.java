package com.springbootaws.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBook is a Querydsl query type for Book
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBook extends EntityPathBase<Book> {

    private static final long serialVersionUID = -1412071787L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBook book = new QBook("book");

    public final QProduct _super;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> CreateDateTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDateTime;

    //inherited
    public final StringPath creator;

    //inherited
    public final NumberPath<Long> id;

    public final StringPath isbn = createString("isbn");

    // inherited
    public final com.springbootaws.domain.member.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifyDate;

    //inherited
    public final StringPath name;

    //inherited
    public final NumberPath<Integer> price;

    //inherited
    public final NumberPath<Integer> quantity;

    public final StringPath writer = createString("writer");

    public QBook(String variable) {
        this(Book.class, forVariable(variable), INITS);
    }

    public QBook(Path<? extends Book> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBook(PathMetadata metadata, PathInits inits) {
        this(Book.class, metadata, inits);
    }

    public QBook(Class<? extends Book> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QProduct(type, metadata, inits);
        this.CreateDateTime = _super.CreateDateTime;
        this.createDateTime = _super.createDateTime;
        this.creator = _super.creator;
        this.id = _super.id;
        this.member = _super.member;
        this.modifyDate = _super.modifyDate;
        this.name = _super.name;
        this.price = _super.price;
        this.quantity = _super.quantity;
    }

}

