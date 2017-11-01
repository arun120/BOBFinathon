/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTful;

import Database.TransactionDetails;
import Util.ServerDetails;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.HTTPClient;

/**
 *
 * @author Fluffy
 */
@Path("Transaction")
public class Transaction {
    
     @Context
    private UriInfo context;

    /**
     * Creates a new instance of BeneficiaryResource
     */
    public Transaction() {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJson(String body) {
        String res=null; 
        try {
             System.out.println(body);
             
             JSONParser parse=new JSONParser();
             JSONObject json=(JSONObject) parse.parse(body);
             String id=(String)json.get("id");
             String amount=(String)json.get("amount");
             String transfertype=(String)json.get("transfertype");
            TransactionDetails td=TransactionDetails.getById(id);
            String acnumber=td.getSaccount();
            if(transfertype.equals("NEFT"))
            res=HTTPClient.postBOB("NEFTFundsTrf",  "{" +
"    \"Sender_Acct\": \""+ acnumber+ "\" ," +
"    \"Beneficiarry_Acct\": \" "+ td.getAcnumber()+" \"," +
"    \"Beneficiary_Bank\": \" "+ td.getBank()+" \"," +
"    \"Beneficiary_Branch\": \" "+ td.getBranch()+ "\"," +
"    \"Beneficiary_IFSC_cd\": \" " + td.getIfsc()+ " \"," +
"    \"Beneficiary_Acct_Type\": \" " + td.getType()+ " \"," +
"    \"Trans_Amt\": "+ amount +" ," +
"    \"Tran_Rmks\": \"Offline Transfer App\"" +
"}");
            else
            res=HTTPClient.postBOB("RTGSFundsTrf",   "{" +
"    \"Sender_Acct\": \""+ acnumber+ "\" ," +
"    \"Beneficiarry_Acct\": \" "+ td.getAcnumber()+" \"," +
"    \"Beneficiary_Bank\": \" "+ td.getBank()+" \"," +
"    \"Beneficiary_Branch\": \" "+ td.getBranch()+ "\"," +
"    \"Beneficiary_IFSC_cd\": \" " + td.getIfsc()+ " \"," +
"    \"Beneficiary_Acct_Type\": \" " + td.getType()+ " \"," +
"    \"Trans_Amt\": "+ amount +" ," +
"    \"Tran_Rmks\": \"Offline Transfer App\"" +
"}");
                
            
         } catch (ParseException ex) {
             Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
            
             
             return res;
    }
}
