/*
 * This file is generated by jOOQ.
 */
package task.NewOpportunityPortal.db.tables.records;


import java.sql.Timestamp;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import task.NewOpportunityPortal.db.tables.Messages__;


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
public class Messages__Record extends UpdatableRecordImpl<Messages__Record> implements Record5<Long, Long, Long, String, Timestamp> {

    private static final long serialVersionUID = -1029038985;

    /**
     * Setter for <code>public.messages__.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.messages__.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.messages__.creatorId</code>.
     */
    public void setCreatorid(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.messages__.creatorId</code>.
     */
    public Long getCreatorid() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.messages__.chatId</code>.
     */
    public void setChatid(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.messages__.chatId</code>.
     */
    public Long getChatid() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.messages__.text</code>.
     */
    public void setText(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.messages__.text</code>.
     */
    public String getText() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.messages__.created_at</code>.
     */
    public void setCreatedAt(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.messages__.created_at</code>.
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Long, Long, String, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, Long, Long, String, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Messages__.MESSAGES__.ID;
    }

    @Override
    public Field<Long> field2() {
        return Messages__.MESSAGES__.CREATORID;
    }

    @Override
    public Field<Long> field3() {
        return Messages__.MESSAGES__.CHATID;
    }

    @Override
    public Field<String> field4() {
        return Messages__.MESSAGES__.TEXT;
    }

    @Override
    public Field<Timestamp> field5() {
        return Messages__.MESSAGES__.CREATED_AT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getCreatorid();
    }

    @Override
    public Long component3() {
        return getChatid();
    }

    @Override
    public String component4() {
        return getText();
    }

    @Override
    public Timestamp component5() {
        return getCreatedAt();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getCreatorid();
    }

    @Override
    public Long value3() {
        return getChatid();
    }

    @Override
    public String value4() {
        return getText();
    }

    @Override
    public Timestamp value5() {
        return getCreatedAt();
    }

    @Override
    public Messages__Record value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public Messages__Record value2(Long value) {
        setCreatorid(value);
        return this;
    }

    @Override
    public Messages__Record value3(Long value) {
        setChatid(value);
        return this;
    }

    @Override
    public Messages__Record value4(String value) {
        setText(value);
        return this;
    }

    @Override
    public Messages__Record value5(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public Messages__Record values(Long value1, Long value2, Long value3, String value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached Messages__Record
     */
    public Messages__Record() {
        super(Messages__.MESSAGES__);
    }

    /**
     * Create a detached, initialised Messages__Record
     */
    public Messages__Record(Long id, Long creatorid, Long chatid, String text, Timestamp createdAt) {
        super(Messages__.MESSAGES__);

        set(0, id);
        set(1, creatorid);
        set(2, chatid);
        set(3, text);
        set(4, createdAt);
    }
}