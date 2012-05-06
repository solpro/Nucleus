package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Party;
import in.solpro.nucleus.apps.common.PartyItemDiscount;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

public class PartyItemDiscountHelper extends GenericHelper
{
    private void validate( PartyItemDiscount itemdiscount ) throws Exception
    {
        int i = 0;
        Date xfrom, xto, yfrom, yto;

        PartyItemDiscount itemdiscount1;
        List<PartyItemDiscount> list = (List<PartyItemDiscount>) getPartyItemDiscounts( itemdiscount.getParty() );
        for ( i = 0; i < list.size(); i++ )
        {
            itemdiscount1 = list.get( i );
            xfrom = itemdiscount.getDatefrom();
            xto = itemdiscount.getDateto();
            yfrom = itemdiscount1.getDatefrom();
            yto = itemdiscount1.getDateto();
            if ( ( xfrom.before( yfrom ) && xto.before( yfrom ) ) || ( xfrom.after( yto ) && xto.after( yto ) ) )
            {
                continue;
            }
            else
            {
                System.out.println( "DISCOUNT DATES OVERLAP !!" );
                throw new Exception( "DISCOUNT DATES OVERLAP !!" );
            }
        }
    }

    public PartyItemDiscountHelper()
    {
        super( PartyItemDiscount.class );
    }

    public void addPartyItemDiscount( PartyItemDiscount partyitemdiscount ) throws Exception
    {
        partyitemdiscount.validateAndUpdate();
        if ( partyitemdiscount.getDatefrom().before( partyitemdiscount.getDateto() ) )
        {
            validate( partyitemdiscount );
            save( partyitemdiscount );
        }
        else
        {
            System.out.println( "DATEFROM SHOULD BE LESS TAHN DATETO !!" );
            throw new Exception( "DATEFROM SHOULD BE LESS TAHN DATETO !!" );
        }
        System.out.println( "partyItemDiscount Saved" );
    }

    public PartyItemDiscount getPartyItemDiscount( Integer id ) throws Exception
    {
        PartyItemDiscount partyitemdiscount = (PartyItemDiscount) find( id );
        partyitemdiscount.validateAndUpdate();
        return partyitemdiscount;
    }

    
    @SuppressWarnings("unchecked")
    public List<PartyItemDiscount> getPartyItemDiscounts(Party party)
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM PartyItemDiscount pc WHERE pc.party.id=:ledgerid AND pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        query.setParameter( "ledgerid",party.getId());
        
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<PartyItemDiscount> getAllPartyItemDiscounts()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM PartyItemDiscount pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );        
        return query.getResultList();
    }

    public void updatePartyItemDiscount( PartyItemDiscount partyitemdiscount ) throws Exception
    {
        partyitemdiscount.validateAndUpdate();
        update( partyitemdiscount );
    }

}