package com.zerobase.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationEntity is a Querydsl query type for ReservationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationEntity extends EntityPathBase<ReservationEntity> {

    private static final long serialVersionUID = 954441454L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationEntity reservationEntity = new QReservationEntity("reservationEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> headCount = createNumber("headCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isVisited = createBoolean("isVisited");

    public final QMemberEntity memberEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DatePath<java.time.LocalDate> reservationDate = createDate("reservationDate", java.time.LocalDate.class);

    public final StringPath reservationKey = createString("reservationKey");

    public final TimePath<java.time.LocalTime> reservationTime = createTime("reservationTime", java.time.LocalTime.class);

    public final QStoreEntity storeEntity;

    public QReservationEntity(String variable) {
        this(ReservationEntity.class, forVariable(variable), INITS);
    }

    public QReservationEntity(Path<? extends ReservationEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationEntity(PathMetadata metadata, PathInits inits) {
        this(ReservationEntity.class, metadata, inits);
    }

    public QReservationEntity(Class<? extends ReservationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberEntity = inits.isInitialized("memberEntity") ? new QMemberEntity(forProperty("memberEntity")) : null;
        this.storeEntity = inits.isInitialized("storeEntity") ? new QStoreEntity(forProperty("storeEntity"), inits.get("storeEntity")) : null;
    }

}

