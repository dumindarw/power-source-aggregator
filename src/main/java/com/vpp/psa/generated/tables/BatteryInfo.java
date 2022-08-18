/*
 * This file is generated by jOOQ.
 */
package com.vpp.psa.generated.tables;


import com.vpp.psa.generated.Keys;
import com.vpp.psa.generated.PsaDb;
import com.vpp.psa.generated.tables.records.BatteryInfoRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BatteryInfo extends TableImpl<BatteryInfoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>psa_db.battery_info</code>
     */
    public static final BatteryInfo BATTERY_INFO = new BatteryInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BatteryInfoRecord> getRecordType() {
        return BatteryInfoRecord.class;
    }

    /**
     * The column <code>psa_db.battery_info.id</code>.
     */
    public final TableField<BatteryInfoRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>psa_db.battery_info.name</code>.
     */
    public final TableField<BatteryInfoRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(100).defaultValue(DSL.field("NULL", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>psa_db.battery_info.post_code</code>.
     */
    public final TableField<BatteryInfoRecord, Integer> POST_CODE = createField(DSL.name("post_code"), SQLDataType.INTEGER.defaultValue(DSL.field("NULL", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>psa_db.battery_info.watt_capacity</code>.
     */
    public final TableField<BatteryInfoRecord, Double> WATT_CAPACITY = createField(DSL.name("watt_capacity"), SQLDataType.DOUBLE.defaultValue(DSL.field("NULL", SQLDataType.DOUBLE)), this, "");

    private BatteryInfo(Name alias, Table<BatteryInfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private BatteryInfo(Name alias, Table<BatteryInfoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>psa_db.battery_info</code> table reference
     */
    public BatteryInfo(String alias) {
        this(DSL.name(alias), BATTERY_INFO);
    }

    /**
     * Create an aliased <code>psa_db.battery_info</code> table reference
     */
    public BatteryInfo(Name alias) {
        this(alias, BATTERY_INFO);
    }

    /**
     * Create a <code>psa_db.battery_info</code> table reference
     */
    public BatteryInfo() {
        this(DSL.name("battery_info"), null);
    }

    public <O extends Record> BatteryInfo(Table<O> child, ForeignKey<O, BatteryInfoRecord> key) {
        super(child, key, BATTERY_INFO);
    }

    @Override
    public Schema getSchema() {
        return PsaDb.PSA_DB;
    }

    @Override
    public Identity<BatteryInfoRecord, Integer> getIdentity() {
        return (Identity<BatteryInfoRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<BatteryInfoRecord> getPrimaryKey() {
        return Keys.KEY_BATTERY_INFO_PRIMARY;
    }

    @Override
    public List<UniqueKey<BatteryInfoRecord>> getKeys() {
        return Arrays.<UniqueKey<BatteryInfoRecord>>asList(Keys.KEY_BATTERY_INFO_PRIMARY, Keys.KEY_BATTERY_INFO_BATTERY_INFO_NAME_UNQ);
    }

    @Override
    public BatteryInfo as(String alias) {
        return new BatteryInfo(DSL.name(alias), this);
    }

    @Override
    public BatteryInfo as(Name alias) {
        return new BatteryInfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BatteryInfo rename(String name) {
        return new BatteryInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BatteryInfo rename(Name name) {
        return new BatteryInfo(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, Integer, Double> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}