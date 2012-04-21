package in.solpro.nucleus.apps.common;

public enum LedgerGroupType
{
    LIABILITIES, ASSETS, EXPENSES, INCOME, PRIMARY;
    private static final String STR_LIABILITIES = "Liabilities";

    private static final String STR_ASSETS = "Assets";

    private static final String STR_EXPENSES = "Expenses";

    private static final String STR_INCOME = "Income";

    private static final String STR_PRIMARY = "Primary";

    public String toString()
    {
        switch ( this )
        {
        case LIABILITIES:
            return STR_LIABILITIES;
        case ASSETS:
            return STR_ASSETS;
        case EXPENSES:
            return STR_EXPENSES;
        case INCOME:
            return STR_INCOME;
        case PRIMARY:
            return STR_PRIMARY;
        }
        return STR_EXPENSES;
    }

    public int toInt()
    {
        switch ( this )
        {
        case ASSETS:
            return 0;
        case LIABILITIES:
            return 1;
        case EXPENSES:
            return 2;
        case INCOME:
            return 3;
        case PRIMARY:
            return 4;
        }
        return 2;
    }

    public static LedgerGroupType getTypeFromInt( int type )
    {
        switch ( type )
        {
        case 0:
            return LedgerGroupType.ASSETS;
        case 1:
            return LedgerGroupType.LIABILITIES;
        case 2:
            return LedgerGroupType.EXPENSES;
        case 3:
            return LedgerGroupType.INCOME;
        case 4:
            return LedgerGroupType.PRIMARY;
        }
        return LedgerGroupType.EXPENSES;
    }

    public static LedgerGroupType getTypeFromString( String type )
    {
        if ( type.equals( STR_LIABILITIES ) )
        {
            return LedgerGroupType.LIABILITIES;
        }
        else if ( type.equals( STR_ASSETS ) )
        {
            return LedgerGroupType.ASSETS;
        }
        else if ( type.equals( STR_EXPENSES ) )
        {
            return LedgerGroupType.EXPENSES;
        }
        else if ( type.equals( STR_INCOME ) )
        {
            return LedgerGroupType.INCOME;
        }
        else if ( type.equals( STR_PRIMARY ) )
        {
            return LedgerGroupType.PRIMARY;
        }
        return LedgerGroupType.EXPENSES;
    }

}
