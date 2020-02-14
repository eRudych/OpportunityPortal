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
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import task.NewOpportunityPortal.db.Indexes;
import task.NewOpportunityPortal.db.Keys;
import task.NewOpportunityPortal.db.Public;
import task.NewOpportunityPortal.db.tables.records.Messages__Record;


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
public class Messages__ extends TableImpl<Messages__Record> {

    private static final long serialVersionUID = -1086098542;

    /**
     * The reference instance of <code>public.messages__</code>
     */
    public static final Messages__ MESSAGES__ = new Messages__();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Messages__Record> getRecordType() {
        return Messages__Record.class;
    }

    /**
     * The column <code>public.messages__.id</code>.
     */
    public final TableField<Messages__Record, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.messages__.creatorId</code>.
     */
    public final TableField<Messages__Record, Long> CREATORID = createField(DSL.name("creatorId"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.messages__.chatId</code>.
     */
    public final TableField<Messages__Record, Long> CHATID = createField(DSL.name("chatId"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.messages__.text</code>.
     */
    public final TableField<Messages__Record, String> TEXT = createField(DSL.name("text"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.messages__.created_at</code>.
     */
    public final TableField<Messages__Record, Timestamp> CREATED_AT = createField(DSL.name("created_at"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * Create a <code>public.messages__</code> table reference
     */
    public Messages__() {
        this(DSL.name("messages__"), null);
    }

    /**
     * Create an aliased <code>public.messages__</code> table reference
     */
    public Messages__(String alias) {
        this(DSL.name(alias), MESSAGES__);
    }

    /**
     * Create an aliased <code>public.messages__</code> table reference
     */
    public Messages__(Name alias) {
        this(alias, MESSAGES__);
    }

    private Messages__(Name alias, Table<Messages__Record> aliased) {
        this(alias, aliased, null);
    }

    private Messages__(Name alias, Table<Messages__Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Messages__(Table<O> child, ForeignKey<O, Messages__Record> key) {
        super(child, key, MESSAGES__);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MESSAGES___PKEY);
    }

    @Override
    public UniqueKey<Messages__Record> getPrimaryKey() {
        return Keys.MESSAGES___PKEY;
    }

    @Override
    public List<UniqueKey<Messages__Record>> getKeys() {
        return Arrays.<UniqueKey<Messages__Record>>asList(Keys.MESSAGES___PKEY);
    }

    @Override
    public List<ForeignKey<Messages__Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Messages__Record, ?>>asList(Keys.MESSAGES____FK_MESSAGE_USER, Keys.MESSAGES____FK_MESSAGE_CHAT);
    }

    public User user() {
        return new User(this, Keys.MESSAGES____FK_MESSAGE_USER);
    }

    public Chats_ chats_() {
        return new Chats_(this, Keys.MESSAGES____FK_MESSAGE_CHAT);
    }

    @Override
    public Messages__ as(String alias) {
        return new Messages__(DSL.name(alias), this);
    }

    @Override
    public Messages__ as(Name alias) {
        return new Messages__(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Messages__ rename(String name) {
        return new Messages__(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Messages__ rename(Name name) {
        return new Messages__(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Long, String, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
