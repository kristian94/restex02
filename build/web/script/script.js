$(document).ready(function () {

    var table = document.createElement("table");
    $("body").append(table);

    var Person = function (fname, lname, phone, id) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.id = id;
    };

    var makeHeaders = function () {
        var thr = document.createElement("tr");
        var th1 = document.createElement("th");
        var th2 = document.createElement("th");
        var th3 = document.createElement("th");
        var th4 = document.createElement("th");
        $(th1).text("First Name");
        $(th2).text("Last Name");
        $(th3).text("Phone");
        $(th4).text("ID");

        $(thr).append(th1);
        $(thr).append(th2);
        $(thr).append(th3);
        $(thr).append(th4);

        $(table).append(thr);
    };

    var editPerson = function (id) {
        $.get("http://localhost:8080/RestEx02/api/person/" + id, function (data) {
            $(":text:first").val(data.fname);
            $(":text:nth-of-type(2)").val(data.lname);
            $(":text:nth-of-type(3)").val(data.phone);
            $(":hidden:first").val(data.id);
            $("#visible-id").text("Id: " + data.id);
        });

    };
    
    var delPerson = function (id) {
        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/RestEx02/api/person/' + id,
            
            complete: function(){
                getPersons();
            }
        });
        
        
    };

    var resetForm = function () {
        $(":text:first").val("");
        $(":text:nth-of-type(2)").val("");
        $(":text:nth-of-type(3)").val("");
        $(":hidden:first").val("auto");
        $("#visible-id").text("Id: auto");
    };


    getPersons = function () {
        $(table).empty();
        makeHeaders();
        $.getJSON("http://localhost:8080/RestEx02/api/person", function (data) {
            $(data).each(function () {



                var tr = document.createElement("tr");
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var td5 = document.createElement("td");
                var td6 = document.createElement("td");

                var editButton = document.createElement("button");
                var delButton = document.createElement("button");

                $(editButton).text("Edit");
                $(delButton).text("Delete");

                $(editButton).attr("person-id", this.id);
                $(delButton).attr("person-id", this.id);

                $(editButton).click(function () {
                    editPerson($(this).attr("person-id"));
                });
                
                $(delButton).click(function(){
                    delPerson($(this).attr("person-id"));
                });

                $(td1).text(this.fname);
                $(td2).text(this.lname);
                $(td3).text(this.phone);
                $(td4).text(this.id);

                $(tr).append(td1);
                $(tr).append(td2);
                $(tr).append(td3);
                $(tr).append(td4);
                $(tr).append(editButton);
                $(tr).append(delButton);

                $(table).append(tr);


            });
        });
    };
    getPersons();

    $("#resetButton").click(function () {
        resetForm();

    });

    $("#listButton").click(function () {
        getPersons();
    });

    $("#personForm").submit(function () {

        var fname = $(":text:first").val();
        var lname = $(":text:nth-of-type(2)").val();
        var phone = $(":text:nth-of-type(3)").val();
        var id = $(":hidden:first").val();

        if (!$(":text:first").val() ||
                !$(":text:nth-of-type(2)").val() ||
                !$(":text:nth-of-type(3)").val()) {
            alert("Some fields are empty!");
            return false;
        }

        var p = new Person(fname, lname, phone, id);

        //if(id == "auto") p.id = null;

        var jsonString = JSON.stringify(p);

        console.log(jsonString);

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/RestEx02/api/person",
            data: jsonString,
            //dataType: "json",
            header: {
                'Content-Type': 'application/json'
            }
            //contentType: "application/json"


        });

        getPersons();
        resetForm();
        return false;
    });




});





