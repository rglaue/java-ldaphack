package org.tigris.scarab.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  */
public class ROptionOptionMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.tigris.scarab.om.map.ROptionOptionMapBuilder";


    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("scarab");

        dbMap.addTable("SCARAB_R_OPTION_OPTION");
        TableMap tMap = dbMap.getTable("SCARAB_R_OPTION_OPTION");

        tMap.setPrimaryKeyMethod("none");


              tMap.addForeignPrimaryKey(
                "SCARAB_R_OPTION_OPTION.OPTION1_ID", new Integer(0) , "SCARAB_ATTRIBUTE_OPTION" ,
                "OPTION_ID");
                    tMap.addForeignPrimaryKey(
                "SCARAB_R_OPTION_OPTION.OPTION2_ID", new Integer(0) , "SCARAB_ATTRIBUTE_OPTION" ,
                "OPTION_ID");
                    tMap.addForeignKey(
                "SCARAB_R_OPTION_OPTION.RELATIONSHIP_ID", new Integer(0) , "SCARAB_OPTION_RELATIONSHIP" ,
                "RELATIONSHIP_ID");
                    tMap.addColumn("SCARAB_R_OPTION_OPTION.WEIGHT", new Integer(0));
                    tMap.addColumn("SCARAB_R_OPTION_OPTION.PREFERRED_ORDER", new Integer(0));
          }
}
