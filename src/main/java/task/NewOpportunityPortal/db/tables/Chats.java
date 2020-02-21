/*
 * This file is generated by jOOQ.
 */
package task.NewOpportunityPortal.db.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import task.NewOpportunityPortal.db.Indexes;
import task.NewOpportunityPortal.db.Keys;
import task.NewOpportunityPortal.db.Public;
import task.NewOpportunityPortal.db.tables.records.ChatsRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Chats extends TableImpl<ChatsRecord> {

    private static final long serialVersionUID = 786859632;

    /**
     * The reference instance of <code>public.chats</code>
     */
    public static final Chats CHATS = new Chats();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChatsRecord> getRecordType() {
        return ChatsRecord.class;
    }

    /**
     * The column <code>public.chats.id</code>.
     */
    public final TableField<ChatsRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.chats.creatorId</code>.
     */
    public final TableField<ChatsRecord, Long> CREATORID = createField(DSL.name("creatorId"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.chats.advertId</code>.
     */
    public final TableField<ChatsRecord, Long> ADVERTID = createField(DSL.name("advertId"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.chats.name</code>.
     */
    public final TableField<ChatsRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.chats.usersId</code>.
     */
    public final TableField<ChatsRecord, Long[]> USERSID = createField(DSL.name("usersId"), org.jooq.impl.SQLDataType.BIGINT.getArrayDataType(), this, "");

    /**
     * The column <code>public.chats.created_at</code>.
     */
    public final TableField<ChatsRecord, Timestamp> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * Create a <code>public.chats</code> table reference
     */
    public Chats() {
        this(DSL.name("chats"), null);
    }

    /**
     * Create an aliased <code>public.chats</code> table reference
     */
    public Chats(String alias) {
        this(DSL.name(alias), CHATS);
    }

    /**
     * Create an aliased <code>public.chats</code> table reference
     */
    public Chats(Name alias) {
        this(alias, CHATS);
    }

    private Chats(Name alias, Table<ChatsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Chats(Name alias, Table<ChatsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Chats(Table<O> child, ForeignKey<O, ChatsRecord> key) {
        super(child, key, CHATS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CHATS_PKEY);
    }

    @Override
    public UniqueKey<ChatsRecord> getPrimaryKey() {
        return Keys.CHATS_PKEY;
    }

    @Override
    public List<UniqueKey<ChatsRecord>> getKeys() {
        return Arrays.<UniqueKey<ChatsRecord>>asList(Keys.CHATS_PKEY);
    }

    @Override
    public List<ForeignKey<ChatsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ChatsRecord, ?>>asList(Keys.CHATS__FK_CHAT_USER, Keys.CHATS__FK_CHAT_ADVERT);
    }

    public User user() {
        return new User(this, Keys.CHATS__FK_CHAT_USER);
    }

    public Adverts adverts() {
        return new Adverts(this, Keys.CHATS__FK_CHAT_ADVERT);
    }

    @Override
    public Chats as(String alias) {
        return new Chats(DSL.name(alias), this);
    }

    @Override
    public Chats as(Name alias) {
        return new Chats(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Chats rename(String name) {
        return new Chats(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Chats rename(Name name) {
        return new Chats(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, Long, Long, String, Long[], Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}
