let pops = document.getElementById("pop");

function openpopup() {
  if (pops.innerHTML != null) pops.classList.add("open-pop");
}
function closepopup() {
  pops.classList.remove("open-pop");
}
let bid = document.getElementById("mc");
function Authentication() {
  // let username = "mgandhi.mg67@gmail.com";
  // let password = "101010";
  let username = localStorage.getItem("User");
  let password = localStorage.getItem("Password");
  let auth = btoa(`${username}:${password}`);
  return auth;
}
let nid = document.getElementById("notify");
let so = document.getElementById("so");

so.addEventListener("click", () => {
  localStorage.clear();
  window.location.assign("/index.html");
});
function notify(message) {
  nid.innerHTML = message;
  nid.style.opacity = 1;
  setInterval(() => {
    nid.style.opacity = 0;
  }, 2000);
}
function addFeedback() {
  let auth = Authentication();
  let cid;
  let feedback;
  let rating;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="cid"><h4>Enter the Customer id</h4></label> <br>
  <input type="text"  id="cid" /> 
  </div>

  <div class="inputElement">
  <label for="fb"><h4>Enter the feedback</h4></label> <br>
  <input type="text"  id="fb" /> 
  </div>

  <div class="inputElement">
  <label for="cr"><h4>Enter the rating</h4></label> <br>
  <input type="text"  id="cr" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    feedback = document.getElementById("fb").value;
    rating = document.getElementById("cr").value;
    fetch("http://localhost:8080/travel/feedback/" + cid, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        feedback: feedback,
        rating: rating,
      }),
    })
      .then((dat) => dat.json())
      .then((data) => {
        console.log(data);
        if (data.message == "Customer not valid") {
          notify(data.message);
        } else if (data.feedbackId != undefined) {
          getData1(data.message);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData1(result) {
  notify("Feedback added successfully with Id " + result);
}
function searchFeedBackByfeedBackId() {
  let auth = Authentication();
  let fid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="fid"><h4>Enter the feedBack id</h4></label> <br>
  <input type="text" name="enviroment" id="fid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    fid = document.getElementById("fid").value;
    // bid.removeChild(div);
    if (fid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/feedback/" + fid, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Feedback not found with the  given id") {
          notify(dat.message);
        } else {
          getData2(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData2(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  div.innerHTML = `<div class="busentry" >
     <h4>FeedBack ID : ${result.feedbackId} </h4>
     <h4>FeedBck : ${result.feedback}</h4>
     <h4>Rating : ${result.rating}</h4>
     <h4>Submission Date : ${result.submitDate} </h4>
     <h4>Customer ID : ${result.customerFeedback.customerId} </h4>
     </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
}

function searchFeedBackByCustomerId() {
  let auth = Authentication();
  let cid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="cid"><h4>Enter the customer id</h4></label> <br>
  <input type="text" name="enviroment" id="cid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/feedback/customer/" + cid, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => {
        console.log(data);
        return data.json();
      })
      .then((dat) => {
        if (dat.message == "Customer gave no feedback yet") {
          notify(dat.message);
        } else if (dat.message == "Customer not found with the  given id") {
          notify(dat.message);
        } else {
          getData3(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData3(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");

  div.innerHTML = `<div class="busentry" >
    <h4>FeedBack ID : ${result.feedbackId} </h4>
    <h4>FeedBck : ${result.feedback}</h4>
    <h4>Rating : ${result.rating}</h4>
    <h4>Submission Date : ${result.submitDate} </h4>
    <h4>Customer ID : ${result.customerFeedback.customerId} </h4>
    </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function viewAllFeedBack() {
  let auth = Authentication();
  bid.innerHTML = null;

  fetch("http://localhost:8080/travel/feedback", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Basic ${auth}`,
    },
  })
    .then((data) => data.json())
    .then((dat) => {
      if (dat.message == "Feedback list is empty") {
        notify(dat.message);
      } else {
        getData4(dat);
      }
    })
    .catch((error) => console.log(error));
}
function getData4(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
    <h4>FeedBack ID : ${element.feedbackId} </h4>
    <h4>FeedBack Description : ${element.feedback}</h4>
    <h4>Rating : ${element.rating}</h4>
    <h4>Submission Date : ${element.submitDate} </h4>
    <h4>Customer ID : ${element.customerFeedback.customerId} </h4>
    </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

//  Package functionalities

function bookPackage() {
  let auth = Authentication();
  let cid;
  let pid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="cid"><h4>Enter the customer id</h4></label> <br>
  <input type="text" name="enviroment" id="cid" /> 
  </div>

  <div class="inputElement">
  <label for="pid"><h4>Enter the package id</h4></label> <br>
  <input type="text" name="enviroment" id="pid" /> 
  </div>

  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    pid = document.getElementById("pid").value;
    // bid.removeChild(div);
    if (cid == "" || pid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/package/" + cid + "/" + pid, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Not valid customer to make booking") {
          notify(dat.message);
        } else if (dat.message == "Package not found") {
          notify(dat.message);
        } else if (dat.message == "Customer is not exist now") {
          notify(dat.message);
        } else if (dat.message == "Package is not exist now") {
          notify(dat.message);
        } else if (dat.bookingId != undefined) {
          getData5(dat.bookingId);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData5(result) {
  notify("Package booked successfully with  id " + result);
}

function searchPackage() {
  let auth = Authentication();
  let bookingId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="bid"><h4>Enter the package id</h4></label> <br>
  <input type="text" name="enviroment" id="bid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    bookingId = document.getElementById("bid").value;
    // bid.removeChild(div);
    if (bookingId == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/package/" + bookingId, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "Booking is not exist now") {
          notify(dat.message);
        } else if (
          dat.message == "No booking found with the given id to view"
        ) {
          notify(dat.message);
        } else if (dat.bookingId != undefined) {
          getData7(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData7(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  div.innerHTML = `<div class="busentry" >
     <h4>Booking Id : ${result.bookingId} </h4>
     <h4>Booking type : ${result.bookingType}</h4>
     <h4>Descroption : ${result.description}</h4>
     <h4>Status : ${result.status} </h4>
     <h4>Booking title : ${result.bookingTitle} </h4>
     <h4>Booking date: ${result.bookingDate} </h4>
     <h4>Package name : ${result.packageName} </h4>
     <h4>Number of person: ${result.number_Of_Person} </h4>
     <h4>Package Id : ${result.apackage.packageId} </h4>
     </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function removePackage() {
  let auth = Authentication();
  let pid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="pid"><h4>Enter the package id</h4></label> <br>
  <input type="text" name="enviroment" id="pid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    pid = document.getElementById("pid").value;
    // bid.removeChild(div);
    if (pid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/package/" + pid, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Unable to cancle already canceld booking") {
          notify(dat.message);
        } else if (
          dat.message == "No booking found with the given id to cancle"
        ) {
          notify(dat.message);
        } else if (dat.bookingId != undefined) {
          getData8(dat.bookingId);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData8(result) {
  console.log(result);
  notify("Booking cancelled successfully with booking id " + result);
}

// Bus functions

function searchBusById() {
  let auth = Authentication();
  let pid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="pid"><h4>Enter the bus id</h4></label> <br>
  <input type="text" name="enviroment" id="pid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    pid = document.getElementById("pid").value;
    // bid.removeChild(div);
    if (pid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/bus/" + pid, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "This bus is not exist now") {
          notify(dat.message);
        } else if (dat.message == "Bus not found") {
          notify(dat.message);
        } else if (dat.busId != undefined) {
          getData9(dat);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData9(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  div.innerHTML = `<div class="singlecontainer" >
        <h4>Bus ID : ${result.busId} </h4>
        <h4>Bus Type : ${result.busType}</h4>
        <h4>Bus Number : ${result.busNumber}</h4>
        <h4>Capacity : ${result.capacity} </h4>
        <h4>Status : ${result.status}</h3>
        </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function searchBusByTravelId() {
  let auth = Authentication();
  let pid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="pid"><h4>Enter the travel id</h4></label> <br>
  <input type="text" name="enviroment" id="pid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    pid = document.getElementById("pid").value;
    // bid.removeChild(div);
    if (pid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/bus/travels/" + pid, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "This travels is not available now") {
          notify(dat.message);
        } else if (dat.message == "No bus is added by this traveler yet") {
          notify(dat.message);
        } else if (dat.message == "Travels not found to add bus") {
          notify(dat.message);
        } else getData10(dat);
      })
      .catch((error) => console.log(error));
  });
}
function getData10(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="singlecontainer" >
    <h4>Bus ID : ${element.busId} </h4>
    <h4>Bus Type : ${element.busType}</h4>
    <h4>Bus Number : ${element.busNumber}</h4>
    <h4>Capacity : ${element.capacity} </h4>
    <h4>Status : ${element.status}</h3>
    </div>`);
  });

  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function viewAllBuses() {
  let auth = Authentication();
  fetch("http://localhost:8080/travel/bus", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Basic ${auth}`,
    },
  })
    .then((data) => {
      console.log(data);
      return data.json();
    })
    .then((dat) => {
      if (dat.message == "Bus list is empty") {
        notify(dat.message);
      } else getData11(dat);
    })
    .catch((error) => console.log(error));
}
function getData11(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
     <h4>Bus ID : ${element.busId} </h4>
     <h4>Bus Type : ${element.busType}</h4>
     <h4>Bus Number : ${element.busNumber}</h4>
     <h4>Capacity : ${element.capacity} </h4>
     <h4>Status : ${element.status}</h3>
     </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function searchCustomer() {
  let auth = Authentication();
  let cid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="cid"><h4>Enter the customer id</h4></label> <br>
  <input type="text" name="enviroment" id="cid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/customer/" + cid, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "This customer is not exist now") {
          notify(dat.message);
        } else if (
          dat.message == "Customer not found with the given id to delete"
        ) {
          notify(dat.message);
        } else if (dat.customerId != undefined) {
          getData12(dat);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData12(element) {
  console.log(element);
  bid.innerHTML = null;
  //  <h4>Customer password : ${element.customerPassword}</h4>
  let div = document.createElement("div");
  div.innerHTML = `<div class="busentry" >
  <h4>Customer id : ${element.customerId} </h4>
   <h4>Customer name : ${element.customerName} </h4>
   <h4>Address : ${element.address}</h4>
   <h4>Aadhar id : ${element.aadharId} </h4>
   <h4>Gender : ${element.gender} </h4>
   <h4>Country : ${element.country} </h4>
   <h4>Status : ${element.status} </h4>
   <h4>Mobile number : ${element.mobileNo} </h4>
   <h4>Email : ${element.email} </h4>
   <h4>Role : ${element.role} </h4>
   </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}
function removeCustomer() {
  let auth = Authentication();
  let cid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="cid"><h4>Enter the customer id</h4></label> <br>
  <input type="text" name="enviroment" id="cid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/customer/delete/" + cid, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Unable to remove already deleted customer") {
          notify(dat.message);
        } else if (
          dat.message == "Customer not found with the given id to delete"
        ) {
          notify(dat.message);
        } else if (dat.customerId != undefined) {
          getData13(dat.customerId);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData13(result) {
  console.log(result);
  notify("Customer removed successfully with customer id " + result);
}

function viewAllDestination() {
  let auth = Authentication();
  fetch("http://localhost:8080/travel/Destinations", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Basic ${auth}`,
    },
  })
    .then((data) => {
      console.log(data);
      return data.json();
    })
    .then((dat) => {
      if (dat.message == "Destination list is empty") notify(dat.message);
    })
    .catch((error) => console.log(error));
}
function getData14(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
     <h4>Destination ID : ${element.desId} </h4>
     <h4>Destination Name : ${element.name}</h4>
     <h4>Destination Enviroment : ${element.desEnvironment}</h4>
     <h4>Destination status : ${element.status} </h4>
     </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function searchDestination() {
  let auth = Authentication();
  let desId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="desid"><h4>Enter the destination id</h4></label> <br>
  <input type="text" name="enviroment" id="desid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    desId = document.getElementById("desid").value;
    // bid.removeChild(div);
    console.log(desId);
    if (desId == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/Destination/" + desId, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "This destinaton is not available now") {
          notify(dat.message);
        } else if (dat.message == "Destination not found with the given id") {
          notify(dat.message);
        } else if (dat.desId != undefined) {
          getData15(dat);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData15(element) {
  console.log(element);
  bid.innerHTML = null;
  let div = document.createElement("div");
  div.innerHTML = `<div class="busentry" >
  <h4>Destination ID : ${element.desId} </h4>
  <h4>Destination Name : ${element.name}</h4>
  <h4>Destination Enviroment : ${element.desEnvironment}</h4>
  <h4>Destination status : ${element.status} </h4>
  </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function searchDestinationByBusId() {
  let auth = Authentication();
  let desId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="desid"><h4>Enter the bus id</h4></label> <br>
  <input type="text" name="enviroment" id="desid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    desId = document.getElementById("desid").value;
    // bid.removeChild(div);
    console.log(desId);
    if (desId == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/Destination/travels/" + desId, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "Bus is not exist now") {
          notify(dat.message);
        } else if (dat.message == "Given bus not go any destination yet") {
          notify(dat.message);
        } else if (dat.message == "Bus not found with the given id") {
          notify(dat.message);
        } else getData16(dat);
      })
      .catch((error) => console.log(error));
  });
}
function getData16(data) {
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];

  data.forEach((element) => {
    arr.push(`<div class="busentry" >
    <h4>Destination ID : ${element.desId} </h4>
    <h4>Destination Name : ${element.name}</h4>
    <h4>Destination Enviroment : ${element.desEnvironment}</h4>
    <h4>Destination status : ${element.status} </h4>
    </div>`);
  });

  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}
// Hotel functions

function viewAllHotel() {
  let auth = Authentication();
  fetch("http://localhost:8080/travel/Hotel", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Basic ${auth}`,
    },
  })
    .then((data) => {
      console.log(data);
      return data.json();
    })
    .then((dat) => {
      if (dat.message == "Hotels list is empty") {
        notify(dat.message);
      } else getData17(dat);
    })
    .catch((error) => console.log(error));
}
function getData17(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
     <h4>Hotel ID : ${element.hotelId} </h4>
     <h4>Hotel name : ${element.hotelName}</h4>
     <h4>Hote type : ${element.hotelType}</h4>
     <h4>Hotel Description : ${element.hotelDescription} </h4>
     <h4>Hotel Address : ${element.address} </h4>
     <h4>Hotel Rent : ${element.rent} </h4>
     <h4>Hotel stats : ${element.stats} </h4>
     <h4>Hotel status : ${element.status} </h4>
     </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function showByDesId() {
  let auth = Authentication();
  let did;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="did"><h4>Enter the Destination id</h4></label> <br>
  <input type="text" name="enviroment" id="did" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    did = document.getElementById("did").value;
    // bid.removeChild(div);
    if (did == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/Hotel/Destination/" + did, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => {
        console.log(data);
        return data.json();
      })
      .then((dat) => {
        if (dat.message == "This destination is not available now") {
          notify(dat.message);
        } else if (dat.message == "Empty hotel list") {
          notify(dat.message);
        } else if (dat.message == "No destination is found with the given id") {
          notify(dat.message);
        } else {
          getData18(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData18(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
     <h4>Hotel ID : ${element.hotelId} </h4>
     <h4>Hotel name : ${element.hotelName}</h4>
     <h4>Hote type : ${element.hotelType}</h4>
     <h4>Hotel Description : ${element.hotelDescription} </h4>
     <h4>Hotel Address : ${element.address} </h4>
     <h4>Hotel Rent : ${element.rent} </h4>
     <h4>Hotel stats : ${element.stats} </h4>
     <h4>Hotel status : ${element.status} </h4>
     </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

// Travels functions

function searchTravel() {
  let auth = Authentication();
  let cid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="cid"><h4>Enter the travel id</h4></label> <br>
  <input type="text" name="enviroment" id="cid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/travels/" + cid, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "Travel is not available now") {
          notify(dat.message);
        } else if (dat.message == "Travels not found to view") {
          notify(dat.message);
        } else if (dat.travelsId != undefined) {
          getData19(dat);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData19(element) {
  console.log(element);
  bid.innerHTML = null;
  //  <h4>Customer password : ${element.customerPassword}</h4>
  let div = document.createElement("div");
  div.innerHTML = `<div class="busentry" >
  <h4>Travels id : ${element.travelsId} </h4>
   <h4>Travels name : ${element.travelsName} </h4>
   <h4>Agent name : ${element.agentName}</h4>
   <h4>Status : ${element.status} </h4>
   <h4>Address : ${element.address} </h4>
   <h4>Contact : ${element.contact} </h4>

   </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function viewAllTravel() {
  let auth = Authentication();
  fetch("http://localhost:8080/travel/travels", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Basic ${auth}`,
    },
  })
    .then((data) => {
      console.log(data);
      return data.json();
    })
    .then((dat) => {
      if (dat.message == "Travels list is empty") {
        notify(dat.message);
      } else {
        console.log(dat);
        getData20(dat);
      }
    })
    .catch((error) => console.log(error));
}
function getData20(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
     <h4>Travels ID : ${element.travelsId} </h4>
     <h4>Travels name : ${element.travelsName}</h4>
     <h4>Agent name : ${element.agentName}</h4>
     <h4>Current status : ${element.status} </h4>
     <h4>Travles address : ${element.address} </h4>
     <h4>Contact number : ${element.contact} </h4>
     </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}
// Route functions

function viewAllRoute() {
  let auth = Authentication();
  fetch("http://localhost:8080/travel/route", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Basic ${auth}`,
    },
  })
    .then((data) => {
      console.log(data);
      return data.json();
    })
    .then((dat) => {
      if (dat.message == "Empty route list") {
        notify(dat.message);
      } else getData21(dat);
    })
    .catch((error) => console.log(error));
}
function getData21(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
     <h4>Route ID : ${element.routeId} </h4>
     <h4>Route from : ${element.routeFrom}</h4>
     <h4>Route to : ${element.routeTo}</h4>
     <h4>Departure time : ${element.departureTime} </h4>
     <h4>Status : ${element.status} </h4>
     <h4>Arrival time : ${element.arrivalTime} </h4>
     <h4>DOJ : ${element.doj} </h4>
     <h4>Pick up point : ${element.pickupPoint} </h4>
     <h4>Fare : ${element.fare} </h4>
     </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}

function searchRoute() {
  let auth = Authentication();
  let routeId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="rid"><h4>Enter the route id</h4></label> <br>
  <input type="text" name="enviroment" id="rid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    routeId = document.getElementById("rid").value;
    // bid.removeChild(div);
    if (routeId == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/route/" + routeId, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "Route is not available now") {
          notify(dat.message);
        } else if (dat.message == "Route not found to view") {
          notify(dat.message);
        } else if (dat.routeId != undefined) {
          getData22(dat);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData22(element) {
  console.log(element);
  bid.innerHTML = null;
  let div = document.createElement("div");
  div.innerHTML = `<div class="busentry" >
  <h4>Route ID : ${element.routeId} </h4>
  <h4>Route from : ${element.routeFrom}</h4>
  <h4>Route to : ${element.routeTo}</h4>
  <h4>Departure time : ${element.departureTime} </h4>
  <h4>Status : ${element.status} </h4>
  <h4>Arrival time : ${element.arrivalTime} </h4>
  <h4>DOJ : ${element.doj} </h4>
  <h4>Pick up point : ${element.pickupPoint} </h4>
  <h4>Fare : ${element.fare} </h4>
  </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}
//  Payment function

function makePayment() {
  let auth = Authentication();
  let bookingId;
  let customerId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="bid"><h4>Enter the booking id</h4></label> <br>
  <input type="text"  id="bid" /> 
  </div>

  <div class="inputElement">
  <label for="cid"><h4>Enter the customer id</h4></label> <br>
  <input type="text"  id="cid" /> 
  </div>

  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    bookingId = document.getElementById("bid").value;
    customerId = document.getElementById("cid").value;
    fetch(
      "http://localhost:8080/travel/Payment/" + bookingId + "/" + customerId,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Basic ${auth}`,
        },
      }
    )
      .then((dat) => dat.json())
      .then((data) => {
        if (data.message == "Please enter valid customer id") {
          notify(data.message);
        } else if (data.message == "Package Booking not exist now") {
          notify(data.message);
        } else if (data.message == "No hotel booking found with the given id") {
          notify(data.message);
        } else if (data.paymentId != undefined) {
          getData23(data.paymentId);
          window.location.assign("user.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData23(result) {
  notify("Payment  successfully done with Id " + result);
}

function canclePayment() {
  let auth = Authentication();
  let pid;
  let cid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="pid"><h4>Enter the payment id</h4></label> <br>
  <input type="text" name="enviroment" id="pid" /> 
  </div>

  <div class="inputElement">
  <label for="cid"><h4>Enter the customer id</h4></label> <br>
  <input type="text" name="enviroment" id="cid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    pid = document.getElementById("pid").value;
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "" || pid == "") window.location.assign("user.html");
    fetch("http://localhost:8080/travel/Payment/" + pid + "/" + cid, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Unable to cancle already cancled payment") {
          notify(dat.message);
        } else if (dat.message == "Payment details not found") {
          notify(dat.message);
        } else if (dat.message == "Not valid customer") {
          notify(dat.message);
        } else if (dat.paymentId != undefined) {
          getData24(dat.paymentId);
        } else window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData24(result) {
  console.log(result);
  notify("Payment cancled with payment id " + result);
}

function viewAllPackage() {
  let auth = Authentication();
  bid.innerHTML = null;

  fetch("http://localhost:8080/travel/Packages", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Basic ${auth}`,
    },
  })
    .then((data) => {
      console.log(data);
      return data.json();
    })
    .then((dat) => {
      if (dat.message == "Package list is empty") {
        notify(dat.message);
      } else {
        getData25(dat);
      }
    })
    .catch((error) => console.log(error));
}
function getData25(data) {
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  data.forEach((result) => {
    arr.push(`<div class="busentry" >
    <h4>Package Id : ${result.packageId} </h4>
    <h4>Package name : ${result.packageName}</h4>
    <h4>Hotel type : ${result.hotelType}</h4>
    <h4>Days and Night : ${result.daysAndNight} </h4>
    <h4>Package season : ${result.packageSeason} </h4>
    <h4>Package description 1 : ${result.packageDescription1} </h4>
    <h4>Package description 2 : ${result.packageDescription2} </h4>
    <h4>Package description 3 : ${result.packageDescription3} </h4>
    <h4>Package description 4 : ${result.packageDescription4} </h4>
    <h4>Package description 5 : ${result.packageDescription5} </h4>
    <h4>Package cost : ${result.packageCost} </h4>
    <h4>Status : ${result.status} </h4>
    </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("user.html");
  });
  bid.append(div);
}
