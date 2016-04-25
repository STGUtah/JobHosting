package com.stgconsulting.api;

import com.stgconsulting.wsdl.InventoriesQuery;
import com.stgconsulting.wsdl.InventoriesQueryResponse;
import com.stgconsulting.wsdl.InventoryAttrType;
import com.stgconsulting.wsdl.InventoryAttrTypeEnumStr;
import com.stgconsulting.wsdl.InventoryAttrValueType;
import com.stgconsulting.wsdl.InventoryAttrsType;
import com.stgconsulting.wsdl.InventoryType;
import com.stgconsulting.wsdl.LicenseFilterEnumStr;
import com.stgconsulting.wsdl.LicenseTypeEnumStr;
import com.stgconsulting.wsdl.QueriesResponse;
import com.stgconsulting.wsdl.Query;
import java.text.SimpleDateFormat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class MonsterClient extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(MonsterClient.class);

  public InventoriesQueryResponse getPostingDetails(Long postingId) {

    InventoriesQuery request = new InventoriesQuery();
    LicenseFilterEnumStr licenseFilter = new LicenseFilterEnumStr();
    licenseFilter.setMonsterId("2");
    licenseFilter.setValue("All");
    request.setLicenseFilter(licenseFilter);
    LicenseTypeEnumStr licenseType = new LicenseTypeEnumStr();
    licenseType.setMonsterId("1");
    licenseType.setValue("Job Inventory");
    request.setLicenseType(licenseType);

    log.info("Requesting job inventory ");

    InventoriesQueryResponse response = (InventoriesQueryResponse) getWebServiceTemplate()
      .marshalSendAndReceive(
        "https://gateway.monster.com:8443/bgwBroker",
        request,
        new SoapActionCallback("https://gateway.monster.com:8443/bgwBroker/"));

    return response;
  }

  public void printResponse(InventoriesQueryResponse response) {

    List<InventoryType> inventoriesReturn = response.getInventories().getInventories();

    if (inventoriesReturn.size() > 0) {
//      log.info("Forecast for " + inventoriesReturn.getCity() + ", " + inventoriesReturn.getState());

      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      for (InventoryType inventory : inventoriesReturn) {

        List<InventoryAttrType> attributes = inventory.getInventoryAttrs().getInventoryAttrs();
        for (InventoryAttrType attribute : attributes) {
          InventoryAttrTypeEnumStr attrType = attribute.getAttrTypeId();
          InventoryAttrValueType attrValue = attribute.getAttrValue();
        }

//        log.info(String.format("%s %s %s°-%s°", format.format(forecast.getDate().toGregorianCalendar().getTime()),
//          forecast.getDesciption(), temperature.getMorningLow(), temperature.getDaytimeHigh()));
//        log.info("");
      }
    } else {
      log.info("No inventory received");
    }
  }

}
