package com.zerobase.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreEntity is a Querydsl query type for StoreEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreEntity extends EntityPathBase<StoreEntity> {

    private static final long serialVersionUID = 1169011587L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreEntity storeEntity = new QStoreEntity("storeEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMemberEntity memberEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath storeKey = createString("storeKey");

    public QStoreEntity(String variable) {
        this(StoreEntity.class, forVariable(variable), INITS);
    }

    public QStoreEntity(Path<? extends StoreEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreEntity(PathMetadata metadata, PathInits inits) {
        this(StoreEntity.class, metadata, inits);
    }

    public QStoreEntity(Class<? extends StoreEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
    }

}

