$("#getPSC").click(function(){
  var psc = $("#psc").val(),
      pscOutput = $("#pscOutput").val();

  var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://localhost:8080/posta/psc/"+psc,
    "method": "GET",
    "headers": {
      "content-type": "application/json",
      "cache-control": "no-cache",
    },
    "processData": false
  }

  $.ajax(settings).done(function (response) {
    $("#pscOutput").empty();
    for (var i = 0; i < response.length/4; i++) {
      $("#pscOutput").append("<span>"+response[i]+"</span><br>");
    }
  }).fail(function (response) {
    $("#pscOutput").empty();
    $("#pscOutput").text("Wrong input!");
  });

});

$("#getObec").click(function(){
  var obec = $("#obec").val(),
      obecOutput = $("#obecOutput").val();

  var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://localhost:8080/posta/city/"+obec,
    "method": "GET",
    "headers": {
      "content-type": "application/json",
      "cache-control": "no-cache",
    },
    "processData": false,
  }

  $.ajax(settings).done(function (response) {
    $("#obecOutput").empty();
    for (var i = 0; i < response.length/4; i++) {
      $("#obecOutput").append("<span>"+response[i]+"</span>");
    }
  }).fail(function (response) {
    $("#obecOutput").empty();
    $("#obecOutput").text("Wrong input!");
  }); 
});

$('#obec').keypress(function (e) {

  if (e.which == 13) {
    var obec = $("#obec").val(),
      obecOutput = $("#obecOutput").val();

  var settings = {
    "async": true,
    "crossDomain": true,
    "url": "http://localhost:8080/posta/city/"+obec,
    "method": "GET",
    "headers": {
      "content-type": "application/json",
      "cache-control": "no-cache",
    },
    "processData": false,
  }

  $.ajax(settings).done(function (response) {
    $("#obecOutput").empty();
    for (var i = 0; i < response.length/4; i++) {
      $("#obecOutput").append("<span>"+response[i]+"</span>");
    }
  }).fail(function (response) {
    $("#obecOutput").empty();
    $("#obecOutput").text("Wrong input!");
  });   
  }
});

