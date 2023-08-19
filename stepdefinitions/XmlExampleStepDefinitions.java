package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class XmlExampleStepDefinitions extends BaseClass {


    /*
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<collection>
    <contact>
        <id>4612392884961280</id>
        <type>PERSON</type>
        <first_name>pawan</first_name>
        <last_name> </last_name>
        <name></name>
        <created_time>1665134522</created_time>
        <updated_time>1665135124</updated_time>
        <last_contacted>0</last_contacted>
        <last_emailed>0</last_emailed>
        <last_campaign_emaild>0</last_campaign_emaild>
        <last_called>0</last_called>
        <viewed_time>0</viewed_time>
        <viewed>
            <viewed_time>0</viewed_time>
        </viewed>
        <star_value>4</star_value>
        <lead_score>92</lead_score>
        <klout_score></klout_score>
        <schema_version>1</schema_version>
        <tags>Lead</tags>
        <tags>Likely Buyer</tags>
        <tagsWithTime>
            <tag>Lead</tag>
            <createdTime>1665134522024</createdTime>
            <availableCount>0</availableCount>
            <entity_type>tag</entity_type>
        </tagsWithTime>
        <tagsWithTime>
            <tag>Likely Buyer</tag>
            <createdTime>1665134522024</createdTime>
            <availableCount>0</availableCount>
            <entity_type>tag</entity_type>
        </tagsWithTime>
        <properties>
            <type>SYSTEM</type>
            <name>first_name</name>
            <value>Pawan</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>last_name</name>
            <value> </value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>email</name>
            <subtype>work</subtype>
            <value>samson@a.com</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>address</name>
            <value>{"zip":"2000","country":"AU","address":"225 George Street","city":"NSW","countryname":"Australia","state":"Sydney"}</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>phone</name>
            <subtype>work</subtype>
            <value>8888888889</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>phone</name>
            <subtype>home</subtype>
            <value>8888888889</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>website</name>
            <subtype>YOUTUBE</subtype>
            <value>www.youtube.com</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>website</name>
            <subtype>LINKEDIN</subtype>
            <value>www.linkedin.com</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>website</name>
            <subtype>URL</subtype>
            <value>www.mywebsite.com</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type text</name>
            <value>My name is ghanshyam</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type date</name>
            <value>1479580200</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type checkbox</name>
            <value>on</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type list</name>
            <value>lemon</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type companies</name>
            <value>["5767466600890368","5114076984246272","5746725247516672"]</value>
        </properties>
        <campaignStatus>
            <start_time>1665134522689</start_time>
            <end_time>1665135124593</end_time>
            <campaign_id>5878516129792000</campaign_id>
            <campaign_name>Aopo</campaign_name>
            <status>5878516129792000-DONE</status>
        </campaignStatus>
        <entity_type>contact_entity</entity_type>
        <source>api</source>
        <formId>0</formId>
        <bulkActionTracker></bulkActionTracker>
        <forceSearch>false</forceSearch>
        <is_duplicate_existed>false</is_duplicate_existed>
        <trashed_time>0</trashed_time>
        <restored_time>0</restored_time>
        <is_duplicate_verification_failed>false</is_duplicate_verification_failed>
        <is_client_import>false</is_client_import>
        <concurrent_save_allowed>true</concurrent_save_allowed>
        <is_lead_converted>false</is_lead_converted>
        <lastCalled>0</lastCalled>
        <lastCampaignEmailed>0</lastCampaignEmailed>
        <lastContacted>0</lastContacted>
        <lastEmailed>0</lastEmailed>
        <lead_converted_time>0</lead_converted_time>
        <lead_source_id>0</lead_source_id>
        <lead_status_id>0</lead_status_id>
        <owner>
            <id>5319672660033536</id>
            <domain>realestateautomation</domain>
            <email>cst3@yopmail.com</email>
            <phone></phone>
            <name>cst3</name>
            <pic>https://d1gwclp1pmzk26.cloudfront.net/img/gravatar/81.png</pic>
            <schedule_id>CST3</schedule_id>
            <calendar_url>https://realestateautomation.agilecrm.com/calendar/CST3</calendar_url>
        </owner>
    </contact>
    <contact>
        <id>5960366592425984</id>
        <type>PERSON</type>
        <first_name>saurav</first_name>
        <last_name>patil</last_name>
        <name></name>
        <created_time>1664466725</created_time>
        <updated_time>1664467295</updated_time>
        <last_contacted>0</last_contacted>
        <last_emailed>0</last_emailed>
        <last_campaign_emaild>0</last_campaign_emaild>
        <last_called>0</last_called>
        <viewed_time>0</viewed_time>
        <viewed>
            <viewed_time>0</viewed_time>
        </viewed>
        <star_value>4</star_value>
        <lead_score>92</lead_score>
        <klout_score></klout_score>
        <schema_version>1</schema_version>
        <tags>Lead</tags>
        <tags>Likely Buyer</tags>
        <tagsWithTime>
            <tag>Lead</tag>
            <createdTime>1664466725182</createdTime>
            <availableCount>0</availableCount>
            <entity_type>tag</entity_type>
        </tagsWithTime>
        <tagsWithTime>
            <tag>Likely Buyer</tag>
            <createdTime>1664466725182</createdTime>
            <availableCount>0</availableCount>
            <entity_type>tag</entity_type>
        </tagsWithTime>
        <properties>
            <type>SYSTEM</type>
            <name>first_name</name>
            <value>saurav</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>last_name</name>
            <value>patil</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>email</name>
            <subtype>work</subtype>
            <value>saurav@walt.ltd</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>address</name>
            <value>{"zip":"2000","country":"AU","address":"225 George Street","city":"NSW","countryname":"Australia","state":"Sydney"}</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>phone</name>
            <subtype>work</subtype>
            <value>7878787878</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>phone</name>
            <subtype>home</subtype>
            <value>757578798</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>website</name>
            <subtype>YOUTUBE</subtype>
            <value>www.youtube.com</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>website</name>
            <subtype>LINKEDIN</subtype>
            <value>www.linkedin.com</value>
        </properties>
        <properties>
            <type>SYSTEM</type>
            <name>website</name>
            <subtype>URL</subtype>
            <value>www.mywebsite.com</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type text</name>
            <value>My name is ghanshyam</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type date</name>
            <value>1479580200</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type checkbox</name>
            <value>on</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type list</name>
            <value>lemon</value>
        </properties>
        <properties>
            <type>CUSTOM</type>
            <name>My custom field of type companies</name>
            <value>["5767466600890368","5114076984246272","5746725247516672"]</value>
        </properties>
        <campaignStatus>
            <start_time>1664466725986</start_time>
            <end_time>1664467295620</end_time>
            <campaign_id>5878516129792000</campaign_id>
            <campaign_name>Aopo</campaign_name>
            <status>5878516129792000-DONE</status>
        </campaignStatus>
        <entity_type>contact_entity</entity_type>
        <source>api</source>
        <formId>0</formId>
        <bulkActionTracker></bulkActionTracker>
        <forceSearch>false</forceSearch>
        <is_duplicate_existed>false</is_duplicate_existed>
        <trashed_time>0</trashed_time>
        <restored_time>0</restored_time>
        <is_duplicate_verification_failed>false</is_duplicate_verification_failed>
        <is_client_import>false</is_client_import>
        <concurrent_save_allowed>true</concurrent_save_allowed>
        <is_lead_converted>false</is_lead_converted>
        <lastCalled>0</lastCalled>
        <lastCampaignEmailed>0</lastCampaignEmailed>
        <lastContacted>0</lastContacted>
        <lastEmailed>0</lastEmailed>
        <lead_converted_time>0</lead_converted_time>
        <lead_source_id>0</lead_source_id>
        <lead_status_id>0</lead_status_id>
        <owner>
            <id>6338953359196160</id>
            <domain>realestateautomation</domain>
            <email>cst2@yopmail.com</email>
            <phone></phone>
            <name>CST2</name>
            <pic>https://d1gwclp1pmzk26.cloudfront.net/img/gravatar/72.png</pic>
            <schedule_id>CST2</schedule_id>
            <calendar_url>https://realestateautomation.agilecrm.com/calendar/CST2</calendar_url>
        </owner>
    </contact>
</collection>
     */
    //@Given("I get all contacts info from get all api")
    public void setup(){

        Header header1 = new Header("Accept","application/xml");

        List<Header> headerList = new ArrayList<>();

        Headers headers = new Headers(headerList);

        response=given()
                .baseUri("https://realestateautomation.agilecrm.com")
                .basePath("/dev/api/contacts")
                .headers(headers)
                .auth().basic("automation@yopmail.com", "2rm1jfdht83gov5qjbs7nbcckr")
                .log()
                .all()
                .when().get();
        response.prettyPrint();
    }

    @Then("I verify the first names from the response.")
    public void iVerifyTheFirstNamesFromTheResponse() {

        // Firstname from 0th index object.
        String firstName = response.body().xmlPath().get("Collection.contact[0].properties[0].value");

        System.out.println(firstName);

        // get the first names from all the contacts.

        int size = response.body().xmlPath().getList("collection.contact").size(); // size of contacts which is 2.

        System.out.println(size);

        for (int i=0;i<size;i++) {
            String path = "collection.contact[" + i + "].properties.value";

            String first_Name = response.body().xmlPath().getString(path);

            System.out.println(first_Name);
        }
            // verify the first name using hamcrest library.

            ValidatableResponse response1 = response.then();
              response1.body("collection.contact[0].properties[0].value",equalTo("Pawan"));
            List<String> names = List.of("Pawan","saurav");

            for (int i=0;i<size;i++){
                String path = "collection.contact["+i+"].properties[0].value";

                String actuslName = response1.extract().path(path);

                String expectName = names.get(i%names.size());

                Assert.assertEquals(expectName,actuslName);
                //response1.body(path,containsString(String.valueOf(names)));
            }

        }
    }

