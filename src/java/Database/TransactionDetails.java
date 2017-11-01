package Database;


import Database.Dbdetails;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 * Created by Fluffy on 23-10-2017.
 */
public class TransactionDetails {

    private String ifsc;
    private String branch;
    private String bank;
    private String acnumber;
    private String type;
    private String saccount;

    public String getSaccount() {
        return saccount;
    }

    public void setSaccount(String saccount) {
        this.saccount = saccount;
    }
    

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAcnumber() {
        return acnumber;
    }

    public void setAcnumber(String acnumber) {
        this.acnumber = acnumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIfsc() {

        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getJson(){

        return new Gson().toJson(this);
    }

    public static TransactionDetails fromJson(String json){

        return new Gson().fromJson(json,TransactionDetails.class);

    }
    
    public String insert(){
    
        String res=null;
        try {
            Connection conn=new Dbdetails().getConnection();
            String sql="insert into beneficiary values(null,?,?,?,?,?,?)";
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setString(1, ifsc);
            stmt.setString(2, bank);
            stmt.setString(3, branch);
            stmt.setString(4, acnumber);
            stmt.setString(5, type);
            stmt.setString(6, saccount);
            stmt.executeUpdate();
            stmt.close();
            sql="select id from beneficiary where acnumber=?";
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, acnumber);
            ResultSet rs=stmt.executeQuery();
            if(rs.last())
                res=rs.getString("id");
                
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
               return res;
     
    }
    
    
    public static TransactionDetails getById(String id){
    
        TransactionDetails td=null;
        
        try {
            Connection conn=new Dbdetails().getConnection();
            String sql="select * from beneficiary where id=?";
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setString(1, id);
            
            ResultSet rs=stmt.executeQuery();

           
            if(rs.next()){
            td=new TransactionDetails();
            td.setAcnumber(rs.getString("acnumber"));
            td.setBank(rs.getString("bank"));
            td.setBranch(rs.getString("branch"));
            td.setIfsc(rs.getString("ifsc"));
            td.setType(rs.getString("type"));
            td.setSaccount(rs.getString("saccount"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            return td;
    }

}
