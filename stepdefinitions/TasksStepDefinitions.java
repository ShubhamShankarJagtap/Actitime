package stepdefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.List;

public class TasksStepDefinitions extends BaseClass{
@Then("I verify tasks info in the response")
    public void verifyResponse(){
    // verify the status code of the response.
    Assert.assertEquals(200,response.getStatusCode());
}

@Then("Verify all tasks for project id {int}")
    public void verifyTaskById(int taskId){
    List<Integer> projectId=response.jsonPath().getList("items.projectId");

    // Assert the execution if the projectID does not present in the response.

     projectId.forEach(id-> Assert.assertEquals(taskId, (int) id));
}

    @Then("I verify all tasks for project id {int}")
    public void iVerifyAllTasksForProjectId(int taskId) {
    List<Integer> projectId = response.jsonPath().getList("items.projectId");

    // Assert the execution if project id is not present in the response.
        projectId.forEach(id->Assert.assertEquals(taskId,(int)id));

        //get the name of zeroth index object.
        String name = response.jsonPath().getString("items[0].name");
        System.out.println("name of the 0th index task is :" + name);

        // get the created field value from the 0th index.
        System.out.println("Created value :" + response.jsonPath().getLong("items[0].created"));

        // get the canModify field value from 0th index
        System.out.println("canModify value from allowedAction " + response.jsonPath().getBoolean("items[0].allowedActions.canModify"));

        //get the 0th index object from the items list from  response.
       LinkedHashMap<String,Object> linkedHashMap =response.jsonPath().getJsonObject("items[0]");

       //get the id of an object
        System.out.println("ID of an object :" + (int)linkedHashMap.get("id"));

        //get the allowedActions object.
        System.out.println("AllowedAction field value "+ linkedHashMap.get("allowedActions"));

        //check the allowedActions field values
        System.out.println(linkedHashMap.get("allowedActions").getClass());

        LinkedHashMap<String,Boolean> allowedAction = (LinkedHashMap<String, Boolean>) linkedHashMap.get("allowedActions");

        //print canModify value
        System.out.println(allowedAction.get("canModify"));

        //print canDelete value
        System.out.println(allowedAction.get("canDelete"));

        System.out.println(response.jsonPath().getJsonObject("items[0]").toString());
}

}
