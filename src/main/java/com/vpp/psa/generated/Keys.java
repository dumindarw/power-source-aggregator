/*
 * This file is generated by jOOQ.
 */
package com.vpp.psa.generated;


import com.vpp.psa.generated.tables.BatteryInfo;
import com.vpp.psa.generated.tables.FlywaySchemaHistory;
import com.vpp.psa.generated.tables.records.BatteryInfoRecord;
import com.vpp.psa.generated.tables.records.FlywaySchemaHistoryRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * psa_db.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<BatteryInfoRecord> KEY_BATTERY_INFO_BATTERY_INFO_NAME_UNQ = Internal.createUniqueKey(BatteryInfo.BATTERY_INFO, DSL.name("KEY_battery_info_battery_info_name_UNQ"), new TableField[] { BatteryInfo.BATTERY_INFO.NAME }, true);
    public static final UniqueKey<BatteryInfoRecord> KEY_BATTERY_INFO_PRIMARY = Internal.createUniqueKey(BatteryInfo.BATTERY_INFO, DSL.name("KEY_battery_info_PRIMARY"), new TableField[] { BatteryInfo.BATTERY_INFO.ID }, true);
    public static final UniqueKey<FlywaySchemaHistoryRecord> KEY_FLYWAY_SCHEMA_HISTORY_PRIMARY = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("KEY_flyway_schema_history_PRIMARY"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
}
