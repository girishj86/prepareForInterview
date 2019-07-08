/*** Global Variables ***/
  var questionsForCurrentTopic = [];
  var currentQuestionCount = 0;
  var currentQuestionIdSelected ;
  var currentTopicIdSelected;
  var currentParentTopicIdSelected;
  var currentTopicIdForUpdate = "";
  
/*** Global Objects ***/
  function topic(topicId,topicName,topicType) {
    this.topicId = topicId,
    this.topicName = topicName,
    this.topicType = topicType,

    this.fontSize = function(){
        if (topicName.length>=28)
          return "5";
        else
          return "6";
    },

    this.renderHTML = function(){  
    	
    //col-lg-3 col-xs-6
    return "<div class='col-lg-4 col-xs-8'>"+
                            "<!-- small box -->"+
                            "<div class='small-box bg-blue' style='border-radius:10px'>"+
                            "<div class='btn-group pull-right'>"+                       
                            "<button type='button' class='btn btn-success dropdown-toggle' data-toggle='dropdown' style='border-radius:10px;padding:8%;'>"+
                              "<span>Actions</span>"+
                              "<span class='sr-only'>Toggle Dropdown</span>"+
                            "</button>"+
                            "<ul class='dropdown-menu' role='menu'>"+
                              "<li><a id='editTopicId'>Edit</a></li>"+
                              "<li><a id='deleteTopicId'>Delete</a></li>"+
                            "</ul>"+
                          "</div>"+
                            "<div class='inner'>"+
                                    "<h3 id="+this.topicId+" class='topic' style='cursor:pointer'><font size="+this.fontSize()+">"+this.topicName+"</font></h3>"+
                                "</div>"+
                          "</div>"+
                        "</div><!-- ./col -->";

    }


  }
  
  function QuestionAccordion(topicName,topicId,question_id,question_description,answer_explanation,additional_reference,isSearch){
      this.topicName = topicName,
      this.topicId = topicId,
      this.question_id = question_id,
      this.question_description = question_description,
      this.answer_explanation = answer_explanation,
      this.additional_reference = additional_reference,
      this.isSearch = isSearch,
     /* this.importance = importance,
      this.notes = notes,*/
      
      this.setTopicName = function(){
          var topicNameForQuestionsF = document.getElementById("topicNameForQuestions");
          if(topicNameForQuestionsF){
          topicNameForQuestionsF.innerHTML = "<b>"+topicName+"</b>";
          }
      },
      
      this.buildQuestionContent = function(){
          var accordionDiv = document.getElementById("accordion");
          var accordionQuestionDivId = "accordionQuestionDiv"+this.question_id;
          var accordionQuestionId = "accordionQuestion"+this.question_id;
          var collapseHref = "collapse"+this.question_id;
          var accordionAnswerId = "accordionAnswer"+this.question_id;
          var accordionReferencesId = "accordionReferences"+this.question_id;
          var accordionContent = "<div id='"+accordionQuestionDivId+"' class='panel box box-primary'>"+
                                      "<div class='box-header with-border'>"+
                                          "<h4 class='box-title'>"+
                                              "<a id='"+accordionQuestionId+"' data-toggle='collapse' data-parent='#accordion' href='#"+collapseHref+"'>"+
                                                  this.question_description+
                                              "</a>"+
                                          "</h4>";
          if(isSearch==false) {
              accordionContent=accordionContent+
                                          "<div class='btn-group pull-right'>"+
                                          "<div>"+
                                          "<img id='editQuestionAccordionId' src='/prepareForInterview/resources/img/icon_edit_document.png' style='cursor:pointer;margin-top:3%'>"+
                                          "<img id='deleteQuestionAccordionId' src='/prepareForInterview/resources/img/icon_trash.png' style='cursor:pointer'>"+
                                          "</div>"+
                                        "</div>";
          }
          accordionContent=accordionContent+          
                                      "</div>"+
                                      "<div id='"+collapseHref+"' class='panel-collapse collapse'>"+
                                          "<div class='box-body'>"+
                                              "<div id='"+accordionAnswerId+"'>"+
                                                    this.answer_explanation+
                                              "</div>"+
                                          "<div id='"+accordionReferencesId+"'>"+
                                          "<p><u><i>References</i></u></p>"+
                                                    this.additional_reference+
                                          "</div>"+
            
                                          "</div>"+
                                      "</div>"+
                                 "</div>";
          accordionDiv.innerHTML+=accordionContent;
      },
      
      this.renderHTML = function(){
          this.setTopicName();
          this.buildQuestionContent();
          //$('.panel-collapse.in').collapse('hide');
          
      }
  }
 
  function question(topicName,topicId,question_id,question_description,answer_explanation,additional_reference){
	  this.topicName = topicName,
	  this.topicId = topicId,
	  this.question_id = question_id,
	  this.question_description = question_description,
	  this.answer_explanation = answer_explanation,
	  this.additional_reference = additional_reference,
	 /* this.importance = importance,
	  this.notes = notes,*/
	  
	  this.setTopicName = function(){
		  var topicNameForQuestionsF = document.getElementById("topicNameForQuestions");
		  topicNameForQuestionsF.innerHTML = "<b>"+topicName+"</b>";
	  },
	  this.fillQuestionDescription = function(){
		  var questionContentP = document.getElementById("questionContent");
		  questionContentP.innerHTML = this.question_description;
	  },
	  
	  this.fillAnswerExplanation = function(){
		  var answerExplanationContentP = document.getElementById("answerExplanationContent");
		  answerExplanationContentP.innerHTML = this.answer_explanation;
	  },
	  
	  this.fillAdditionalReference = function(){
		  var additionalReferenceP = document.getElementById("referencesContent");
		  additionalReferenceP.innerHTML = this.additional_reference;
	  },
	  
	  this.fillImportance = function(){
		  var importanceP = document.getElementById("importance");
		  importanceP.innerHTML = this.importance;
	  },
	  
	  this.fillNotes = function(){
		  var notesP = document.getElementById("notes");
		  notesP.innerHTML = this.notes;
	  },
	  
	  this.renderHTML = function(){
		  this.setTopicName();
		  this.fillQuestionDescription();
		  this.fillAnswerExplanation();
		  this.fillAdditionalReference();
		  //this.fillImportance();
		  //this.fillNotes();
		  
	  }
	  
  }

/*** Global methods ***/
  
  function onWindowLoad(pathName){
	  
	  var topicIdSelected;
	  if(pathName.indexOf("/prepareForInterview/questions/") > -1){
		  topicIdSelected = pathName.replace("/prepareForInterview/questions/","");
		  currentTopicIdSelected = topicIdSelected;
		  onQuestionsLoad(topicIdSelected);
	  }
	  if(pathName == "/prepareForInterview/home" || pathName == "/prepareForInterview/home"){
	      getAllMainTopics(function(topics) {
	            currentParentTopicIdSelected = "home";
	            currentTopicIdSelected = "home";
	            showTopics(topics);
	            hide("addQuestionIdLink");

	        });
	  }
	  
	  if(pathName.indexOf("/prepareForInterview/topic/") > -1){
        	      
	    topicIdSelected = pathName.replace("/prepareForInterview/topic/", "");
        getTopicByTopicId(topicIdSelected, function(topic) {
            if (topic && topic.topic_id == topicIdSelected) {
                currentParentTopicIdSelected = topicIdSelected;
                currentTopicIdSelected = topicIdSelected;
                getAllSubTopicsByTopicId(topicIdSelected, function(subTopics) {
                    if (subTopics && subTopics.length > 0) {
                        updateTopicsHTML(subTopics);
                    } else {
                        getQuestionsByTopicId(topicIdSelected, function(questions){
                            if(questions && questions.length > 0){
                                window.location.pathname = "/prepareForInterview/questions/"+topicIdSelected;
                            }
                        });
                        show("addQuestionIdLink");
                    }
                    show("addTopicId");
                });
            }
            else 
            {
                window.location.pathname = "/prepareForInterview/home";
            }
        });
	  }
	  if(pathName.indexOf("/prepareForInterview/search") > -1){
	      var query = getParameterByName('query');
	      
	      getAllSearchResults(query,function(questionsPayload){
	          $(".overlay").remove();
	          if(questionsPayload && questionsPayload.length>0){
	                 for(var count=0;count<questionsPayload.length;count++){
	                   var firstQuestion = questionsPayload[count];
	                   var questionObject = new QuestionAccordion(firstQuestion.topic.topic_name,firstQuestion.topic.topic_id,firstQuestion.question_id,
	                           firstQuestion.question_description,firstQuestion.answer_explanation,firstQuestion.additional_reference,true);
	                   currentQuestionIdSelected = firstQuestion.question_id;
	                   questionObject.renderHTML();
	                 }
               }
	      });
	      
	  }
	  updateBreadCrumbLinks(topicIdSelected);
 }
  
  function onQuestionsLoad(topicIdSelected){
      
      getQuestionsByTopicId(topicIdSelected, function(questionsPayload){
               questionsForCurrentTopic = questionsPayload;
               currentQuestionCount = 0;
               
               if(questionsPayload && questionsPayload.length>0){
                 for(var count=0;count<questionsPayload.length;count++){
                   var firstQuestion = questionsPayload[count];
                   var questionObject = new QuestionAccordion(firstQuestion.topic.topic_name,firstQuestion.topic.topic_id,firstQuestion.question_id,
                           firstQuestion.question_description,firstQuestion.answer_explanation,firstQuestion.additional_reference,false);
                   currentQuestionIdSelected = firstQuestion.question_id;
                   questionObject.renderHTML();
                   show("questionDiv");
                   hide("addTopicId");
                   show("addQuestionIdLink");
                   //togglePreviousNext(currentQuestionCount, questionsForCurrentTopic); 
                 }
                 //$('.panel-collapse.in').collapse('hide');  
                   
               } else{
                   
                   window.location.href = "/prepareForInterview/topic/"+topicIdSelected;
               }

            

         });
     }
  
  function getParameterByName(name) {
      var url = window.location.href;
      name = name.replace(/[\[\]]/g, "\\$&");
      var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
          results = regex.exec(url);
      if (!results) return null;
      if (!results[2]) return '';
      return decodeURIComponent(results[2].replace(/\+/g, " "));
  }
  
  /*function onQuestionsLoad(topicIdSelected){
	  
   getQuestionsByTopicId(topicIdSelected, function(questionsPayload){
		  	questionsForCurrentTopic = questionsPayload;
		  	currentQuestionCount = 0;
		  	
		  	if(questionsPayload && questionsPayload.length>0){
                var firstQuestion = questionsPayload[0];
                var questionObject = new question(firstQuestion.topic.topic_name,firstQuestion.topic.topic_id,firstQuestion.question_id,
                        firstQuestion.question_description,firstQuestion.answer_explanation,firstQuestion.additional_reference);
                currentQuestionIdSelected = firstQuestion.question_id;
                questionObject.renderHTML();
                show("questionDiv");
                hide("addTopicId");
                show("addQuestionIdLink");
                togglePreviousNext(currentQuestionCount, questionsForCurrentTopic);     
                
            } else{
                
                window.location.href = "/prepareForInterview/topic/"+topicIdSelected;
            }

            

	  });
  }*/
  
  function updateBreadCrumbLinks(topicIdSelected){
      //update bread crumb
      var breadCrumbElement = document.getElementById("topicsBreadcrumb");
      if(breadCrumbElement==null) return;
      breadCrumbElement.innerHTML = "<li><a href='/prepareForInterview/home'>Home</a></li>";
      
      getBreadcrumbLink(topicIdSelected,function(parentTopicsLink){
          if(parentTopicsLink && parentTopicsLink.status == "success" ){
              var topicIdLinks = parentTopicsLink.topicIdLink.split("-->");
              var topicNameLinks = parentTopicsLink.topicNameLink.split("-->");
              for(var i=0;i<topicIdLinks.length-1;i++){
                  breadCrumbElement.innerHTML += "<li><a id='"+topicIdLinks[i]+"breadCrumbId' class='breadCrumbLink' style='cursor:pointer'>"+topicNameLinks[i]+"</a></li>"; 
              }
              breadCrumbElement.innerHTML += "<li class='active'>"+topicNameLinks[topicNameLinks.length-1]+"</li>";
          }
      });
  }
  
  function showTopics(){
      
      if(currentParentTopicIdSelected == "home"){
          getAllMainTopics(function(mainTopics) {
              updateTopicsHTML(mainTopics);
          });
      } else {
          getAllSubTopicsByTopicId(currentParentTopicIdSelected,function(subTopics){
              if(subTopics && subTopics.length>0){
                  updateTopicsHTML(subTopics);
                  hide("addQuestionIdLink");
                  } else {
                  var topicsDivId = document.getElementById("topicsDiv");
                  topicsDivId.innerHTML = "";
                  show("addQuestionIdLink");
                  }
                  show("addTopicId");
          
      });
      }
  }
  
  function updateTopicsHTML(topics){
      var topicsDivId = document.getElementById("topicsDiv");
      topicsDivId.innerHTML = "";
      for (var i = 0; i < topics.length; i++) {
          var topicJSON = topics[i];
          var topicObject = new window.topic(topicJSON.topic_id, topicJSON.topic_name, topicJSON.topic_type);
          topicsDivId.innerHTML += topicObject.renderHTML();
      }
  }
  
  function showAlertMessage(alertType,message){
	  var showMessageDiv = document.getElementById("showMessageDiv");
	  var h4 = showMessageDiv.getElementsByTagName("h4")[0];
	  if(alertType == "warning"){
	  showMessageDiv.className+=" alert-warning";
	  h4.innerHTML = "Warning!"
	  }
	  if(alertType == "error"){
		  showMessageDiv.className+=" alert-danger";
		  h4.innerHTML = "Error!";
	  }
	  if(alertType == "info"){
		  showMessageDiv.className+=" alert-info";
		  h4.innerHTML = "Info!"
	  }
	  if(alertType == "success"){
		  showMessageDiv.className+=" alert-success";
		  h4.innerHTML = "Success!"
	  }
	  var alertMessageText = showMessageDiv.getElementsByTagName("p")[0];
	  alertMessageText.innerHTML = message;
	  showMessageDiv.style.display = "block";
	  
  }
  
  function hide(id){
	  var element = document.getElementById(id);
	  if(element){
		  element.style.display = "none";
	  }
  }
  
  function show(id){
	  var element = document.getElementById(id);
	  if(element){
		  element.style.display = "block";
	  }
  }
  

  function togglePreviousNext(currentQuestionCount, questionsForCurrentTopic) {

    if (currentQuestionCount > 0) {
        $('#previousButtonId').prop('disabled', false);
    } else {
        $('#previousButtonId').prop('disabled', true);
    }
    if (currentQuestionCount < questionsForCurrentTopic.length - 1) {
        $('#nextButtonId').prop('disabled', false);
    } else {
        $('#nextButtonId').prop('disabled', true);
    }
    
    if(questionsForCurrentTopic.length == 1 || questionsForCurrentTopic.length == 0){
        $('#previousButtonId').prop('disabled', true);
        $('#nextButtonId').prop('disabled', true);
    }
    
    if(currentQuestionCount == 0 && questionsForCurrentTopic.length == 1){
        
        var currentQuestion = questionsForCurrentTopic[currentQuestionCount];
        var currentQuestionToBeDisplayed = new question(currentQuestion.topic.topic_name,currentQuestion.topic.topic_id,currentQuestion.question_id,
                currentQuestion.question_description,currentQuestion.answer_explanation,currentQuestion.additional_reference);
        currentQuestionToBeDisplayed.renderHTML();
        
     }

}
  

  function getURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}
  
/** Ajax Methods * */
  
  function getAllMainTopics(callback){
      var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/topics/rest/listAllMainTopics'
        });
     
      promise.done(callback);
      
  }
  
  function getAllSearchResults(query,callback){
      var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/rest/questions/searchQuery/'+query
      });
      promise.done(callback);
  }
  
  
  function getQuestionsByTopicId(topicId,callback){
	  var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/rest/questions/'+topicId
        });
	 
	  promise.done(callback);
	  
  }
  
  function getAllSubTopicsByTopicId(topicId,callback){
	  var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/topics/rest/listSubTopics/'+topicId
        });
	 
	  promise.done(callback);
	  
  }
  
  function getBreadcrumbLink(topicId,callback){
	  var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/topics/rest/parentTopicsLink/'+topicId
        });
	 
	  promise.done(callback);
  }
  
  function getParentTopic(topicId,callback){
      var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/topics/rest/parentTopic/'+topicId
        });
     
      promise.done(callback);
  }
  
  function getTopicByTopicId(topicId,callback){
      var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/topics/rest/'+topicId
        });
     
      promise.done(callback);
  }
  
  function deleteTopicByTopicId(topicId,callback){
      var promise = $.ajax({
          cache: false,
          type: "DELETE",
          url: '/prepareForInterview/topics/rest/deleteTopic/'+topicId
        });
     
      promise.done(callback);
  }
  
  function checkIfTopicContainsSubTopicsOrQuestions(topicId,callback){
      var promise = $.ajax({
          cache: false,
          type: "GET",
          url: '/prepareForInterview/topics/rest/checkTopicBeforeDelete/'+topicId
        });
     
      promise.done(callback);
  }
  
  
  function deleteQuestionByQuestionId(questionId,callback){
      var promise = $.ajax({
          cache: false,
          type: "DELETE",
          url: '/prepareForInterview/rest/questions/deleteQuestion/'+questionId
        });
     
      promise.done(callback);
  }
  
  function saveOrUpdateTopic(topicToBeSaved,callback){
      var promise = $.ajax({
          cache: false,
          type: "POST",
          contentType:"application/json; charset=utf-8",
          data: JSON.stringify(topicToBeSaved),
          url: '/prepareForInterview/topics/rest/addOrEditTopic'
        });
     
      promise.done(callback);
  }
  
  function saveOrUpdateQuestion(question,callback){
	  var promise = $.ajax({
          cache: false,
          type: "POST",
          contentType:"application/json; charset=utf-8",
          data: JSON.stringify(question),
          url: '/prepareForInterview/rest/questions/addOrEditQuestion'
        });
	 
	  promise.done(callback);
  }
  
  
 
/*** JQuery code ***/
$(function() {

    /*$('#topicNameButton').hover(
            function(){
                $(this).addClass('open');
                },
            function(){
                $(this).removeClass('open');    
                }); */

    //$('[data-toggle="tooltip"]').tooltip();
    
    $(document.body).on('click','#searchQueryBtn',function(e){
        var searchQueryText = $('#searchQuery').val();
        var win = window.open('/prepareForInterview/search?query='+searchQueryText, '_blank');
        win.focus();
    });
    
    $(document.body).on('click', '#editTopicId', function(e) {
        var topicIdForUpdate = e.target.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[0].id;
        currentTopicIdForUpdate = topicIdForUpdate;
        $('#topicTitleId').text("Edit Topic");

        getTopicByTopicId(topicIdForUpdate, function(topicForUpdate) {
            $('#topicIdTopicModalForm').val(topicForUpdate.topic_id);
            $('#topicNameTopicModalForm').val(topicForUpdate.topic_name);
            $('#btnTopicSubmit').text("Update");
            $("#topicModal").modal('show');
        });


    });

    $(document.body).on('click', '#deleteTopicId', function(e) {
        var topicIdForDelete = e.target.parentNode.parentNode.parentNode.nextSibling.childNodes[0].id;

        checkIfTopicContainsSubTopicsOrQuestions(topicIdForDelete, function(response) {
            if (response.status == "false") {

                getTopicByTopicId(topicIdForDelete, function(topicToBeRemoved) {

                    deleteTopicByTopicId(topicIdForDelete, function(result) {
                        if (result.success == "true") {
                            $('#dialogTitleId').text("Success");
                            $('#dialogContentId').css("color", "green");
                            $('#dialogMessageId').text("Topic has been deleted successfully");
                            showTopics();
                        }
                        else {
                            $('#dialogTitleId').text("Error");
                            $('#dialogContentId').css("color", "red");
                            $('#dialogMessageId').text("Couldn't delete the topic! Please try again!");
                        }
                        $('#dialogModal').modal('show');
                    });
                });
            }
            else {
                $('#dialogTitleId').text("Error");
                $('#dialogContentId').css("color", "red");
                $('#dialogMessageId').text(response.message);
                $('#dialogModal').modal('show');
            }

        });

    });

    $(document.body).on('click', '#btnTopicSubmit', function(e) {

        var topicToBeSaved = {};
        topicToBeSaved.topic_id = $('#topicIdTopicModalForm').val();
        topicToBeSaved.topic_name = $('#topicNameTopicModalForm').val();
        topicToBeSaved.parent_topic_id = currentTopicIdSelected;
        topicToBeSaved.old_topic_id = currentTopicIdForUpdate;
        currentParentTopicIdSelected = currentTopicIdSelected;
        if (currentParentTopicIdSelected == "home") {
            topicToBeSaved.topic_type = "main";
        }
        else {
            topicToBeSaved.topic_type = "sub";
        }

        saveOrUpdateTopic(topicToBeSaved, function(result) {
            if (result.success == "true") {
                currentTopicIdForUpdate = "";
                var updatedTopics;
                $("#topicModal").modal('hide');
                $('#dialogTitleId').text("Success");
                $('#dialogContentId').css("color", "green");
                $('#dialogMessageId').text("Topic has been added or updated successfully");
                $('#dialogModal').modal('show');
                if (window.location.pathname.indexOf("/prepareForInterview/questions") > -1) {
                    window.location.pathname = "/prepareForInterview/topic/" + topicToBeSaved.parent_topic_id;
                }
                else {
                    showTopics();
                }

            }
            else {
                $('#dialogTitleId').text("Error");
                $('#dialogContentId').css("color", "red");
                $('#dialogMessageId').text("Couldn't add or update the topic! Please try again!");
                $('#dialogModal').modal('show');
            }

        });


    });

    $(document.body).on('click', 'a#addTopicId', function() {

        $('#topicIdTopicModalForm').val("");
        $('#topicIdTopicModalFormHelp').text('Make sure topic Id is unique!');
        $('#topicNameTopicModalForm').val("");
        if (currentParentTopicIdSelected == "home") {
            $('#topicTitleId').text("Add new Main Topic");
        }
        else {
            getTopicByTopicId(currentTopicIdSelected, function(topic) {
                $('#topicTitleId').text("Add new sub Topic in " + topic.topic_name);
            });
        }

        $('#btnTopicSubmit').text("Add");
        $("#topicModal").modal('show');
    });

    $(document.body).on('click', 'a#addQuestionIdLink, #addQuestionIdLeft, #addQuestionIdRight', function() {
        getTopicByTopicId(currentTopicIdSelected, function(topic) {
            $('#questionTitleId').text("Add new Question in " + topic.topic_name);
            $('#btnQuestionSubmitHeader').text("Add");
            $('#btnQuestionSubmitFooter').text("Add");
        });

        CKEDITOR.instances['questionIdTextArea'].setData("");
        CKEDITOR.instances['answerIdTextArea'].setData("");
        CKEDITOR.instances['referencesIdTextArea'].setData("");
        $("#questionModal").modal('show');
    });

    /*$(document.body).on('click','a#editQuestionId',function(){
        
    	
    	for(var q = 0;q < questionsForCurrentTopic.length;q++){
    		var questionObjectSelected = questionsForCurrentTopic[q];
    		if (questionObjectSelected && questionObjectSelected.question_id == currentQuestionIdSelected){
    			CKEDITOR.instances['questionIdTextArea'].setData(questionObjectSelected.question_description);
    			CKEDITOR.instances['answerIdTextArea'].setData(questionObjectSelected.answer_explanation);
                CKEDITOR.instances['referencesIdTextArea'].setData(questionObjectSelected.additional_reference);
	  		  				//$('#difficultyLevelIdSelect').text(questionObjectSelected.importance);
    		}
    	}
    	
    	$('#questionTitleId').text("Edit Question");
    	$('#btnQuestionSubmit').text("Update");
    	$("#questionModal").modal('show');
    });*/

    $(document.body).on('click', '#editQuestionAccordionId', function(evt) {
        
        currentQuestionIdSelected = evt.target.parentElement.parentElement.previousSibling.firstChild.id.replace("accordionQuestion","");
        for (var q = 0; q < questionsForCurrentTopic.length; q++) {
            var questionObjectSelected = questionsForCurrentTopic[q];
            if (questionObjectSelected && questionObjectSelected.question_id == currentQuestionIdSelected) {
                CKEDITOR.instances['questionIdTextArea'].setData(questionObjectSelected.question_description);
                CKEDITOR.instances['answerIdTextArea'].setData(questionObjectSelected.answer_explanation);
                CKEDITOR.instances['referencesIdTextArea'].setData(questionObjectSelected.additional_reference);
            //$('#difficultyLevelIdSelect').text(questionObjectSelected.importance);
            }
        }

        $('#questionTitleId').text("Edit Question");
        $('#btnQuestionSubmitHeader').text("Update");
        $('#btnQuestionSubmitFooter').text("Update");
        $("#questionModal").modal('show');
    });
	  			
	  			
	  			$(document.body).on('click','#deleteQuestionAccordionId',function(evt){
	  			    
	  			  currentQuestionIdSelected = evt.target.parentElement.parentElement.previousSibling.firstChild.id.replace("accordionQuestion","");
	  			  deleteQuestionByQuestionId(currentQuestionIdSelected, function(result) {
	  			    	if(result.success == "true"){
	  			    	    var div = document.getElementById("accordionQuestionDiv"+currentQuestionIdSelected);
	  			    	    if(div){
                              div.parentNode.removeChild(div);
	  			    	       }
	  			    	    for(var i=0;i<questionsForCurrentTopic.length;i++){
                              if(currentQuestionIdSelected == questionsForCurrentTopic[i].question_id){
                                questionsForCurrentTopic.splice(i,1);
                                break;
                              }
	  			    	    }  
	  			    	    $('#dialogTitleId').text("Success");
	                        $('#dialogContentId').css("color","green");
	                        $('#dialogMessageId').text("Question has been deleted successfully");
	  			    	} else {
	  			    	    $('#dialogTitleId').text("Error");
	                        $('#dialogContentId').css("color","red");
	                        $('#dialogMessageId').text("Couldn't delete the Question! Please try again!");
	  			    	}
	  			    	$('#dialogModal').modal('show');
	  			    	
	  			    });
	  			    
	  			});
	  			
/*	  			$(document.body).on('click','a#deleteQuestionId',function(){
	  			    
	  			  deleteQuestionByQuestionId(currentQuestionIdSelected,function(result){
	  			    if(result.success == "true"){
                        $('#dialogTitleId').text("Success");
                        $('#dialogContentId').css("color","green");
                        $('#dialogMessageId').text("Question has been deleted successfully");
                        var currentQuestion;
                        for(var i=0;i<questionsForCurrentTopic.length;i++){
                               if(currentQuestionIdSelected == questionsForCurrentTopic[i].question_id){
                                 if(i>0){  
                                     currentQuestion = questionsForCurrentTopic[i-1];
                                 }
                                 if(i == 0){
                                     currentQuestion = questionsForCurrentTopic[i+1];
                                 }
                                 questionsForCurrentTopic.splice(i,1);
                                 break;
                               }
                        }     
                        if(currentQuestion){
                               currentQuestionIdSelected = currentQuestion.question_id;
                               var currentQuestionToBeDisplayed = new question(currentQuestion.topic.topic_name,currentQuestion.topic.topic_id,currentQuestion.question_id,
                                       currentQuestion.question_description,currentQuestion.answer_explanation,currentQuestion.additional_reference);
                               currentQuestionToBeDisplayed.renderHTML();
                               currentQuestionCount--;
                               togglePreviousNext(currentQuestionCount, questionsForCurrentTopic);
                               }
                         else
                               {
                                   hide("questionDiv");
                                   $('#dialogTitleId').text("Warning!");
                                   $('#dialogContentId').css("color","orange");
                                   $('#dialogMessageId').text("Question has been deleted successfully!Please add a topic or a question!!");
                                   show("addTopicId");
                                   show("addQuestionIdLink");
                                   $('#dialogModal').modal('show'); 
                               }
                          }
                   else {
                        $('#dialogTitleId').text("Error");
                        $('#dialogContentId').css("color","red");
                        $('#dialogMessageId').text("Couldn't delete the Question! Please try again!");
                      }
                    $('#dialogModal').modal('show');
	  			  });
	  			     
	  			});*/
	  			
	  			/*$(document.body).on('click','#addReferenceLinkModal',function(){
	  			    var referenceLink = $('#linkReferenceId').text();
	  			    
	  			});*/
	  			
	  			$(document.body).on('click','#btnQuestionSubmit, #btnQuestionSubmitHeader, #btnQuestionSubmitFooter',function(){
                    
                    var questionObject = new Object();
                    if($('#questionTitleId').text() == "Edit Question"){
                      questionObject.question_id = currentQuestionIdSelected;
                    }
                    questionObject.question_description = CKEDITOR.instances['questionIdTextArea'].getData();
                    questionObject.answer_explanation = CKEDITOR.instances['answerIdTextArea'].getData();
                    questionObject.additional_reference = CKEDITOR.instances['referencesIdTextArea'].getData();
                    //questionObject.importance = $("#difficultyLevelIdSelect").val();
                    questionObject.topic_id = currentTopicIdSelected;
                    
                    saveOrUpdateQuestion(questionObject,function(response){
                        if(response.success == "true"){                         
                          var questionAddedUpdated = response.question;
                          var questionStatus = "added";
                          
                          if(questionsForCurrentTopic && questionsForCurrentTopic.length>0){
                            for(var i=0;i<questionsForCurrentTopic.length;i++){
                                 if(questionAddedUpdated.question_id == questionsForCurrentTopic[i].question_id){
                                   questionsForCurrentTopic[i] = questionAddedUpdated;
                                   questionStatus = "updated";
                                   break;
                                 }
                            }
                            if(questionStatus=="added"){
                                questionsForCurrentTopic.push(questionAddedUpdated);
                                var questionAccordion = new QuestionAccordion(questionAddedUpdated.topic.topic_name, questionAddedUpdated.topic.topic_id, questionAddedUpdated.question_id, questionAddedUpdated.question_description, questionAddedUpdated.answer_explanation, questionAddedUpdated.additional_reference,false);
                                questionAccordion.renderHTML();
                            } else if(questionStatus=="updated"){
                              window.location.href = "/prepareForInterview/questions/"+questionAddedUpdated.topic.topic_id;
                            }
                          } else {
                              window.location.href = "/prepareForInterview/questions/"+questionAddedUpdated.topic.topic_id;
                          }
                            
                            $("#dialogTitleId").text("Success");
                            $('#dialogContentId').css("color","green");
                            $('#dialogMessageId').text("Question added or updated successfully!!!");
                            $('#questionModal').modal('hide');
                            $('#dialogModal').modal('show');
                        } else {
                            console.log("Error occured: Question not saved or updated!");
                        }
                    });
                    
                });
	  			
	  			
	  			
	  			
	  			/*$(document.body).on('click','#btnQuestionSubmit',function(){
	  				
	  				var questionObject = new Object();
	  				if($('#questionTitleId').text() == "Edit Question"){
	  				  questionObject.question_id = currentQuestionIdSelected;
	  				}
	  				questionObject.question_description = CKEDITOR.instances['questionIdTextArea'].getData();
	  				questionObject.answer_explanation = CKEDITOR.instances['answerIdTextArea'].getData();
	  				questionObject.additional_reference = CKEDITOR.instances['referencesIdTextArea'].getData();
	  				//questionObject.importance = $("#difficultyLevelIdSelect").val();
	  				questionObject.topic_id = currentTopicIdSelected;
	  				
	  				saveOrUpdateQuestion(questionObject,function(response){
	  					if(response.success == "true"){	  					    
	  					  var questionAddedUpdated = response.question;
	  					  var questionUpdated = false;
	  					  if(questionsForCurrentTopic && questionsForCurrentTopic.length>0){
	  					    for(var i=0;i<questionsForCurrentTopic.length;i++){
	  					         if(questionAddedUpdated.question_id == questionsForCurrentTopic[i].question_id){
	  					           questionsForCurrentTopic[i] = questionAddedUpdated;
	  					           questionUpdated = true;
	  					         
	  		                    var currentQuestionToBeDisplayed = new question(questionAddedUpdated.topic.topic_name,questionAddedUpdated.topic.topic_id,questionAddedUpdated.question_id,
	  		                    questionAddedUpdated.question_description,questionAddedUpdated.answer_explanation,questionAddedUpdated.additional_reference);
	  		                    currentQuestionIdSelected = questionAddedUpdated.question_id;
	  		                    currentQuestionToBeDisplayed.renderHTML();
	  		                    break;
	  					         }
	  					    }
                            if(questionUpdated!=true){
                                questionsForCurrentTopic.push(questionAddedUpdated);
                              }
	  					  } else{
	  					    if(questionUpdated != true){
	  					      window.location.href = "/prepareForInterview/questions/"+questionAddedUpdated.topic.topic_id;
	  					    }
	  					  }
	  					    togglePreviousNext(currentQuestionCount, questionsForCurrentTopic);
	  					    //$("#questionModal").modal('hide');
	  					    $("#dialogTitleId").text("Success");
	  					    $('#dialogContentId').css("color","green");
	  					    $('#dialogMessageId').text("Question added or updated successfully!!!");
	  					    $('#questionModal').modal('hide');
	  					    $('#dialogModal').modal('show');
	  					} else {
	  						console.log("Error occured: Question not saved or updated!");
	  					}
	  				});
	  				
	  			});*/
	  				
	  			$(document.body).on('click','a#logInAsAdminId',function(){
	  				$("#logInAsAdminModal").modal('show');
	  			});
	  			
	  			$("[data-hide]").on("click", function(){
	  					$(this).closest("." + $(this).attr("data-hide")).hide();
	  			});
               $(document.body).on("click",".topic, .breadCrumbLink",function(e){
            	   
            	   var topicId = this.id;
            	   
            	   if(topicId.indexOf("breadCrumbId") > -1){
                       topicId = topicId.replace("breadCrumbId","");
                   }
            	   
            	   currentTopicIdSelected = topicId;
            	   
            	   if(window.location.pathname.indexOf("/prepareForInterview/questions/") > -1){
                       window.location.pathname = "/prepareForInterview/topic/"+topicId;
                       return;
                   }
            	   
            	   getTopicByTopicId(topicId, function(topicSelected){
            	       currentParentTopicIdSelected = topicSelected.parent_topic_id;
            	   });
            	   
            	   
            	   updateBreadCrumbLinks(currentTopicIdSelected);
            	   
            	   getAllSubTopicsByTopicId(topicId,function(topics){
            	       
                       if(topics.length > 0){
                           currentParentTopicIdSelected = topicId;
                           updateTopicsHTML(topics);
                           show("addTopicId");
                           hide("addQuestionIdLink");
                         }
                         else{
                              
                         	getQuestionsByTopicId(topicId,function(questionsPayload){
                         		if(questionsPayload.length == 0){
                         		   var topicsDivId = document.getElementById("topicsDiv");
                                     topicsDivId.innerHTML = "";
                                     show("addQuestionIdLink");
                                     $('#dialogTitleId').text("Warning!");
                                     $('#dialogContentId').css("color","orange");
                                     $('#dialogMessageId').text("Neither Topics Nor Questions found for this topic");
                                     $('#dialogModal').modal('show');
                              	 		}else{
                                     window.location.href = "/prepareForInterview/questions/"+topicId;
                               	 }
                         	});
                         	

                         }
            	   });
            	   
            	 
              
           });
               
               //click on previous
               $(document.body).on("click","#previousButtonId",function(e){
            	   
               	   if(currentQuestionCount == 0)
            	   {
            	   	alert("This is the first question");
            	   	return;
            	   }
             	   	else
            	   	{
             	   		
            	   	var currentQuestionObject = questionsForCurrentTopic[currentQuestionCount-1];
            	   	var currentQuestionToBeDisplayed = new question(currentQuestionObject.topic.topic_name,currentQuestionObject.topic.topic_id,currentQuestionObject.question_id,
            	   			currentQuestionObject.question_description,currentQuestionObject.answer_explanation,currentQuestionObject.additional_reference);
            	   	currentQuestionIdSelected = currentQuestionObject.question_id;
            	   	currentQuestionToBeDisplayed.renderHTML();
            	   	currentQuestionCount--;
            	   	}
               	   
               	 togglePreviousNext(currentQuestionCount, questionsForCurrentTopic);
               	 
            	   });

             //click on next
               $(document.body).on("click","#nextButtonId",function(e){

            	   if(currentQuestionCount == questionsForCurrentTopic.length-1)
            	   {
            	   	alert("This is the last question");
            	   	return;
            	   }
            	   	else
            	   	{
            	   		var currentQuestionObject = questionsForCurrentTopic[currentQuestionCount+1];
                	   	var currentQuestionToBeDisplayed = new question(currentQuestionObject.topic.topic_name,currentQuestionObject.topic.topic_id,currentQuestionObject.question_id,
                	   			currentQuestionObject.question_description,currentQuestionObject.answer_explanation,currentQuestionObject.additional_reference);
                	   	currentQuestionIdSelected = currentQuestionObject.question_id;
                	   	currentQuestionToBeDisplayed.renderHTML();
                	   	currentQuestionCount++;

            	   	}
            	   
            	   togglePreviousNext(currentQuestionCount, questionsForCurrentTopic);
            	   
            	   });
               
               
});




/** initialization code **/

window.onload = function(){
	
	hide("showMessageDiv");
	hide("logInAsAdminId");
	//hide("logOutId");
	hide("addQuestionIdLink");
	
	onWindowLoad(window.location.pathname);
	
}