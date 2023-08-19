let pops = document.getElementById("pop");

function openpopup() {
  if (pops.innerHTML != null) pops.classList.add("open-pop");
}
function closepopup() {
  pops.classList.remove("open-pop");
}

let bid = document.getElementById("mc");
function Authentication() {
  // let username = "pthakur.pt21@gmail.com";
  // let password = "121121";
  let username = localStorage.getItem("User");
  let password = localStorage.getItem("Password");
  let auth = btoa(`${username}:${password}`);
  return auth;
}
let nid = document.getElementById("notify");
let so = document.getElementById("so");

so.addEventListener("click", () => {
  localStorage.clear();
  window.location.assign("index.html");
});

function notify(message) {
  nid.innerHTML = message;
  nid.style.opacity = 1;
  setInterval(() => {
    nid.style.opacity = 0;
  }, 2000);
}

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
    window.location.assign("admin.html");
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
          alert("Bus not found");
          notify(dat.message);
        } else if (dat.busId != undefined) {
          getData1(dat);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData1(result) {
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
    window.location.assign("admin.html");
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
      } else getData2(dat);
    })
    .catch((error) => console.log(error));
}
function getData2(result) {
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
    window.location.assign("admin.html");
  });
  bid.append(div);
}

function removeBus() {
  let auth = Authentication();
  let busid;

  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="bid"><h4>Enter the bus id</h4></label> <br>
  <input type="text" name="enviroment" id="bid" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    busid = document.getElementById("bid").value;
    fetch("http://localhost:8080/travel/bus/" + busid, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Bus not found with the given id to remove") {
          notify(dat.message);
        } else if (dat.message == "Bus is already removed") {
          notify(dat.message);
        } else if (dat.busId != undefined) getData3(dat);
      })
      .catch((error) => console.log(error));
  });
}
function getData3(result) {
  console.log(result);
  alert("Bus Successfully deleted with bud id " + result.busId);
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    pid = document.getElementById("pid").value;
    // bid.removeChild(div);
    if (pid == "") window.location.assign("admin.html");
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
        } else getData4(dat);
      })
      .catch((error) => console.log(error));
  });
}
function getData4(result) {
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
    window.location.assign("admin.html");
  });
}
function addBus() {
  let auth = Authentication();
  let travelId;
  let bustype;
  let busnumber;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="tid"><h4>Enter the travel id</h4></label> <br>
  <input type="text"  id="tid" /> 
  </div>

  <div class="inputElement">
  <label for="bt"><h4>Enter the bus type</h4></label> <br>
  <input type="text"  id="bt" /> 
  </div>

  <div class="inputElement">
  <label for="bn"><h4>Enter the bus number</h4></label> <br>
  <input type="text"  id="bn" /> 
  </div>

  <div class="inputElement">
  <label for="bc"><h4>Enter the capacity</h4></label> <br>
  <input type="text"  id="bc" /> 
  </div>
  </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    travelId = document.getElementById("tid").value;
    fetch("http://localhost:8080/travel/Bus/" + travelId, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        busType: bustype,
        busNumber: busnumber,
        capacity: buscapacity,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Travels not found to add bus") notify(dat.message);
        else if (dat.busId != undefined) getData5(dat.busId);
      })
      .catch((error) => console.log(error));
  });
}
function getData5(result) {
  console.log(result);
  alert("Bus Added successfully with id " + result);
}
function addDestination() {
  let auth = Authentication();
  let desname;
  let desenviroment;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">
    <div class="inputElement">
    <label for="desName"><h4>Enter the destination name</h4></label> <br>
    <input type="text" name="enviroment" id="desName" />
    </div>
    <div class="inputElement">
      <label for="enviroment"><h4>Enter the destination weather</h4></label><br>
      <input type="text" name="env" id="enviroment" />
    </div>

    </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    desname = document.getElementById("desName").value;
    desenviroment = document.getElementById("enviroment").value;
    // bid.removeChild(div);
    fetch("http://localhost:8080/travel/destination", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        name: desname,
        desEnvironment: desenviroment,
      }),
    })
      .then((data) => {
        console.log(data);
        return data.json();
      })
      .then((dat) => {
        if (dat.message == "Travels not found to add bus") {
          notify(dat.message);
        } else if (dat.desId != undefined) {
          getData6(dat.desId, div);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData6(result) {
  console.log(result);
  alert("Destination added successfully with dest id " + result);
  window.location.assign("admin.html");
}

function updateDestination() {
  let auth = Authentication();
  let desname;
  let desenviroment;
  let deseId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="desid"><h4>Enter the destination id</h4></label> <br>
  <input type="text" name="enviroment" id="desid" />
</div>
    <div class="inputElement">
      <label for="desName"><h4>Enter the destination name</h4></label> <br>
      <input type="text" name="enviroment" id="desName" />
    </div>
    <div class="inputElement">
      <label for="enviroment"><h4>Enter the destination weather</h4></label><br>
      <input type="text" name="env" id="enviroment" />
    </div>

    </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    desname = document.getElementById("desName").value;
    desenviroment = document.getElementById("enviroment").value;
    desId = document.getElementById("desid").value;
    // bid.removeChild(div);
    fetch("http://localhost:8080/travel/destination/" + deseId, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        name: desname,
        desEnvironment: desenviroment,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "Destination does not exist") {
          notify(dat.message);
        } else if (dat.desId != undefined) {
          getData7(dat.desId, div);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData7(result) {
  console.log(result);
  notify("Destination Updated successfully with dest id " + result);
  window.location.assign("admin.html");
}

function removeDestination() {
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    desId = document.getElementById("desid").value;
    // bid.removeChild(div);
    if (desId == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/Destination/" + desId, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Unable to remove alerady deleted destination") {
          notify(dat.message);
        } else if (dat.message == "Destination not found with the given id") {
          notify(dat.message);
        } else if (dat.desId != undefined) {
          getData8(dat.desId);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData8(result) {
  console.log(result);
  notify("Destination removed successfully with dest id " + result);
}

function veiwAllDestination() {
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
      else getData9(dat);
    })
    .catch((error) => console.log(error));
}
function getData9(result) {
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
    window.location.assign("admin.html");
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    desId = document.getElementById("desid").value;
    // bid.removeChild(div);
    console.log(desId);
    if (desId == "") window.location.assign("admin.html");
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
          getData10(dat);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData10(element) {
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
    window.location.assign("admin.html");
  });
  bid.append(div);
}

//  Route code

function addRoute() {
  let auth = Authentication();
  let routeFrom;
  let routeTo;
  let derpaureTime;
  let arrivalTime;
  let pickUpPoint;
  let fare;
  let busId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">
  <div class="inputElement">
      <label for="bi"><h4>Enter the bus Id </h4></label> <br>
      <input type="text" name="enviroment" id="bi" />
    </div>

    <div class="inputElement">
      <label for="rf"><h4>Route from </h4></label> <br>
      <input type="text" name="enviroment" id="rf" />
    </div>
    <div class="inputElement">
      <label for="rt"><h4>Route to</h4></label><br>
      <input type="text" name="env" id="rt" />
    </div>
   
    <div class="inputElement">
      <label for="dt"><h4>Departure time</h4></label><br>
      <input type="text" name="env" id="dt" />
    </div>

    <div class="inputElement">
      <label for="at"><h4>Arrival time</h4></label><br>
      <input type="text" name="env" id="at" />
    </div>

    <div class="inputElement">
      <label for="pp"><h4>Pick up point</h4></label><br>
      <input type="text" name="env" id="pp" />
    </div>

    <div class="inputElement">
    <label for="f"><h4>Fare</h4></label><br>
    <input type="text" name="env" id="f" />
  </div>

  </form>
  <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    routeFrom = document.getElementById("rf").value;
    routeTo = document.getElementById("rt").value;
    derpaureTime = document.getElementById("dt").value;
    arrivalTime = document.getElementById("at").value;
    pickUpPoint = document.getElementById("pp").value;
    fare = document.getElementById("f").value;
    busId = document.getElementById("bi").value;
    // bid.removeChild(div);
    fetch("http://localhost:8080/travel/route/" + busId, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        routeFrom: routeFrom,
        routeTo: routeTo,
        departureTime: derpaureTime,
        arrivalTime: arrivalTime,
        pickupPoint: pickUpPoint,
        fare: fare,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Bus is not exist now") {
          notify(dat.message);
          window.location.assign("admin.html");
        } else if (dat.message == "Bus not found with the given id") {
          notify(dat.message);
        } else if (dat.routeId != undefined) {
          getData11(dat.routeId);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData11(result) {
  console.log(result);
  notify("Route added successfully with route id " + result);
}

function updateRoute() {
  let auth = Authentication();
  let routeFrom;
  let routeTo;
  let derpaureTime;
  let arrivalTime;
  let routeId;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">
  <div class="inputElement">
  <label for="ri"><h4>Enter route id </h4></label> <br>
  <input type="text" name="enviroment" id="ri" />
</div>
    <div class="inputElement">
      <label for="rf"><h4>Route from </h4></label> <br>
      <input type="text" name="enviroment" id="rf" />
    </div>
    <div class="inputElement">
      <label for="rt"><h4>Route to</h4></label><br>
      <input type="text" name="env" id="rt" />
    </div>
   
    <div class="inputElement">
      <label for="dt"><h4>Departure time</h4></label><br>
      <input type="text" name="env" id="dt" />
    </div>

    <div class="inputElement">
      <label for="at"><h4>Arrival time</h4></label><br>
      <input type="text" name="env" id="at" />
    </div>

    </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    routeFrom = document.getElementById("rf").value;
    routeTo = document.getElementById("rt").value;
    derpaureTime = document.getElementById("dt").value;
    arrivalTime = document.getElementById("at").value;
    routeId = document.getElementById("ri").value;
    // bid.removeChild(div);
    fetch("http://localhost:8080/travel/route/update/" + routeId, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        routeFrom: routeFrom,
        routeTo: routeTo,
        departureTime: derpaureTime,
        arrivalTime: arrivalTime,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat);
        if (dat.message == "Route is not available to update now") {
          notify(dat.message);
        } else if (dat.message == "Route not found to update") {
          notify(dat.message);
        } else if (dat.routeId != undefined) {
          getData12(dat.routeId);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData12(result) {
  console.log(result);
  notify("Route Updated successfully with route id " + result);
}

function removeRoute() {
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    routeId = document.getElementById("rid").value;
    // bid.removeChild(div);
    if (routeId == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/route/" + routeId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Route is not available to remove now") {
          notify(dat.message);
        } else if (dat.message == "Route not found to remove") {
          notify(dat.message);
        } else if (dat.routeId != undefined) {
          getData13(dat.routeId, div);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData13(result) {
  console.log(result);
  notify("Route removed successfully with route id " + result);
}

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
      } else getData14(dat);
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
    window.location.assign("admin.html");
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    routeId = document.getElementById("rid").value;
    // bid.removeChild(div);
    if (routeId == "") window.location.assign("admin.html");
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
          getData15(dat);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData15(element) {
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
    window.location.assign("admin.html");
  });
  bid.append(div);
}

// Customer functions

function updateCustomer() {
  let auth = Authentication();
  let customerName;
  let customerPassword;
  let address;
  let aadharId;
  let gender;
  let country;
  let mobileNumber;
  let emailAddress;
  let cid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="ci"><h4>Enter customer id</h4></label> <br>
  <input type="text" name="enviroment" id="ci" />
</div>

  <div class="inputElement">
  <label for="cn"><h4>Enter customer name</h4></label> <br>
  <input type="text" name="enviroment" id="cn" />
</div>
    <div class="inputElement">
      <label for="cp"><h4>Enter customer password </h4></label> <br>
      <input type="text" name="enviroment" id="cp" />
    </div>
    <div class="inputElement">
      <label for="ca"><h4>Enter customer address</h4></label><br>
      <input type="text" name="env" id="ca" />
    </div>
   
    <div class="inputElement">
      <label for="ai"><h4>Enter customer aadhar id</h4></label><br>
      <input type="text" name="env" id="ai" />
    </div>

    <div class="inputElement">
      <label for="cg"><h4>Enter customer gender</h4></label><br>
      <input type="text" name="env" id="cg" />
    </div>
    
    <div class="inputElement">
      <label for="cc"><h4>Enter country</h4></label><br>
      <input type="text" name="env" id="cc" />
    </div>
    
    <div class="inputElement">
      <label for="cm"><h4>Enter customer mobile number</h4></label><br>
      <input type="text" name="env" id="cm" />
    </div>

    <div class="inputElement">
    <label for="ce"><h4>Enter email</h4></label><br>
    <input type="text" name="env" id="ce" />
  </div>

  </form>
  <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("ci").value;
    customerName = document.getElementById("cn").value;
    customerPassword = document.getElementById("cp").value;
    address = document.getElementById("ca").value;
    aadharId = document.getElementById("ai").value;
    gender = document.getElementById("cg").value;
    country = document.getElementById("cc").value;
    mobileNumber = document.getElementById("cm").value;
    emailAddress = document.getElementById("ce").value;
    // bid.removeChild(div);
    fetch("http://localhost:8080/travel/customer/update/" + cid, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        customerName: customerName,
        customerPassword: customerPassword,
        address: address,
        aadharId: aadharId,
        gender: gender,
        country: country,
        mobileNo: mobileNumber,
        email: emailAddress,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat.status);
        if (dat.message == "Unable to update already deleted customer") {
          notify(dat.message);
        } else if (
          dat.message == "Customer not found with the given id to update"
        ) {
          notify(dat.message);
        } else if (dat.customerId != undefined) {
          getData16(dat.customerId);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData16(result) {
  console.log(result);
  notify("Customer Updated successfully with customer id " + result);
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "") window.location.assign("admin.html");
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
          getData17(dat.customerId);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData17(result) {
  console.log(result);
  notify("Customer removed successfully with customer id " + result);
}

function viewAllCustomer() {
  let auth = Authentication();
  fetch("http://localhost:8080/travel/customers", {
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
      if (dat.message == "Customer list is empty")
        alert("Customer list is empty");
      else getData18(dat);
    })
    .catch((error) => console.log(error));
}
function getData18(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    //  <h4>Customer password : ${element.customerPassword}</h4>
    arr.push(`<div class="busentry" >
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
     </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "") window.location.assign("admin.html");
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
          getData19(dat);
        } else window.location.assign("admin.html");
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
    window.location.assign("admin.html");
  });
  bid.append(div);
}

//  Hotel function

function addHotel() {
  let auth = Authentication();
  let hotelName;
  let hotelType;
  let hotelDescription;
  let address;
  let rent;
  let status;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
      <label for="di"><h4>Enter destination id </h4></label> <br>
      <input type="text" name="enviroment" id="di" />
    </div>

    <div class="inputElement">
      <label for="hn"><h4>Enter the hotel name </h4></label> <br>
      <input type="text" name="enviroment" id="hn" />
    </div>
    <div class="inputElement">
      <label for="ht"><h4>Enter hotel type</h4></label><br>
      <input type="text" name="env" id="ht" />
    </div>
   
    <div class="inputElement">
      <label for="hd"><h4>Enter hotel description</h4></label><br>
      <input type="text" name="env" id="hd" />
    </div>

    <div class="inputElement">
      <label for="ha"><h4>Enter hotel address</h4></label><br>
      <input type="text" name="env" id="ha" />
    </div>

    <div class="inputElement">
      <label for="hr"><h4>Enter hotel rent</h4></label><br>
      <input type="text" name="env" id="hr" />
    </div>

    <div class="inputElement">
    <label for="hs"><h4>Enter status</h4></label><br>
    <input type="text" name="env" id="hs" />
  </div>

  </form>
  <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    hotelName = document.getElementById("hn").value;
    hotelType = document.getElementById("ht").value;
    hotelDescription = document.getElementById("hd").value;
    address = document.getElementById("ha").value;
    rent = document.getElementById("hn").value;
    status = document.getElementById("hs").value;
    desId = document.getElementById("di").value;
    // bid.removeChild(div);
    if (desId == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/hotel/" + desId, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        hotelName: hotelName,
        hotelType: hotelType,
        hotelDescription: hotelDescription,
        address: address,
        rent: rent,
        status: status,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Invalid destination ID") {
          notify(dat.message);
        } else if (dat.message == "Destination is not available") {
          notify(dat.message);
        } else if (dat.hotelId != undefined) {
          getData20(dat.hotelId);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData20(result) {
  console.log(result);
  notify("Hotel added successfully with hotel id " + result);
}

function removeHotel() {
  let auth = Authentication();
  let hid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="hid"><h4>Enter the hotel id</h4></label> <br>
  <input type="text" name="enviroment" id="hid" /> 
  </div>
  </form>
  <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    hid = document.getElementById("hid").value;
    // bid.removeChild(div);
    if (hid == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/Hotel/" + hid, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Unable to remove already deleted hotel") {
          notify(dat.message);
        } else if (dat.message == "Hotel not found with given id") {
          notify(dat.message);
        } else if (dat.hotelId != undefined) {
          getData21(dat.hotelId);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData21(result) {
  console.log(result);
  notify("Hotel removed successfully with hotel id " + result);
}

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
      if (dat.message == "Hotels list is empty") notify(dat.message);
      else getData22(dat);
    })
    .catch((error) => console.log(error));
}
function getData22(result) {
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
    window.location.assign("admin.html");
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
    window.location.assign("admin.html");
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
          getData23(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData23(result) {
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
    window.location.assign("admin.html");
  });
  bid.append(div);
}

//  Travel functionality

function addTravel() {
  let auth = Authentication();
  let travelName;
  let agentName;
  let address;
  let contact;

  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

    <div class="inputElement">
      <label for="tn"><h4>Enter travels name</h4></label> <br>
      <input type="text" name="enviroment" id="tn" />
    </div>
    <div class="inputElement">
      <label for="an"><h4>Enter agent name</h4></label><br>
      <input type="text" name="env" id="an" />
    </div>
   
    <div class="inputElement">
      <label for="td"><h4>Enter address</h4></label><br>
      <input type="text" name="env" id="td" />
    </div>

    <div class="inputElement">
      <label for="tc"><h4>Enter contact</h4></label><br>
      <input type="text" name="env" id="tc" />
    </div>


    </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    travelName = document.getElementById("tn").value;
    agentName = document.getElementById("an").value;
    address = document.getElementById("td").value;
    contact = document.getElementById("tc").value;

    // bid.removeChild(div);
    // if (desId == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/travels", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        travelsName: travelName,
        agentName: agentName,
        address: address,
        contact: contact,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.travelsId != undefined) {
          getData24(dat.travelsId);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData24(result) {
  console.log(result);
  notify("Travels added successfully with travel id " + result);
}

function removeTravel() {
  let auth = Authentication();
  let tid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="tid"><h4>Enter the travel id</h4></label> <br>
  <input type="text" name="enviroment" id="tid" /> 
  </div>
  </form>
  <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    tid = document.getElementById("tid").value;
    // bid.removeChild(div);
    if (tid == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/travels/remove/" + tid, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Travel is not available to remove") {
          notify(dat.message);
        } else if (dat.message == "Travels not found to remove") {
          notify(dat.message);
        } else if (dat.travelsId != undefined) {
          getData25(dat.travelsId);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData25(result) {
  console.log(result);
  notify("Travels removed successfully with Travel id " + result);
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
      if (dat.message == "Travels list is empty") notify(dat.message);
      else getData26(dat);
    })
    .catch((error) => console.log(error));
}
function getData26(result) {
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
    window.location.assign("admin.html");
  });
  bid.append(div);
}

function updateTravel() {
  let auth = Authentication();
  let agentName;
  let address;
  let contact;
  let tid;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
  <label for="tid"><h4>Enter travels id</h4></label> <br>
  <input type="text" name="enviroment" id="tid" />
</div>


  <div class="inputElement">
  <label for="an"><h4>Enter agent name</h4></label><br>
  <input type="text" name="env" id="an" />
</div>

    <div class="inputElement">
      <label for="ta"><h4>Enter travels address </h4></label> <br>
      <input type="text" name="enviroment" id="ta" />
    </div>
   
   
    <div class="inputElement">
      <label for="ac"><h4>Enter contact number</h4></label><br>
      <input type="text" name="env" id="ac" />
    </div>

    </form>
    <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    tid = document.getElementById("tid").value;
    agentName = document.getElementById("an").value;
    address = document.getElementById("ta").value;
    contact = document.getElementById("ac").value;

    // bid.removeChild(div);
    fetch("http://localhost:8080/travel/travels/update/" + tid, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
      body: JSON.stringify({
        agentName: agentName,
        address: address,
        contact: contact,
      }),
    })
      .then((data) => data.json())
      .then((dat) => {
        console.log(dat.status);
        if (dat.message == "Travel is not available to update") {
          notify(dat.message);
        } else if (dat.message == "Travels not found to update") {
          notify(dat.message);
        } else if (dat.travelsId != undefined) {
          getData27(dat.travelsId);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData27(result) {
  console.log(result);
  notify("Travels Updated successfully with travel id " + result);
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    fid = document.getElementById("fid").value;
    // bid.removeChild(div);
    if (fid == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/feedback/" + fid, {
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
        if (dat.message == "Feedback not found with the  given id") {
          notify(dat.message);
        } else {
          getData27(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData27(result) {
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
    window.location.assign("admin.html");
  });
  bid.append(div);
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    cid = document.getElementById("cid").value;
    // bid.removeChild(div);
    if (cid == "") window.location.assign("admin.html");
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
          getData28(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData28(result) {
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
    window.location.assign("admin.html");
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
    .then((data) => {
      console.log(data);
      return data.json();
    })
    .then((dat) => {
      if (dat.message == "Feedback list is empty") {
        notify(dat.message);
      } else {
        getData29(dat);
      }
    })
    .catch((error) => console.log(error));
}
function getData29(result) {
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
    window.location.assign("admin.html");
  });
  bid.append(div);
}

function viewAllPayment() {
  let auth = Authentication();
  bid.innerHTML = null;

  fetch("http://localhost:8080/travel/Payment", {
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
      if (dat.message == "Payment details list is empty") {
        notify(dat.message);
      } else {
        getData30(dat);
      }
    })
    .catch((error) => console.log(error));
}
function getData30(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
    <h4>Payment ID : ${element.paymentId} </h4>
    <h4>Payment Type : ${element.paymentType}</h4>
    <h4>Date: ${element.localDate}</h4>
    <h4>Payment status : ${element.paymentStatus} </h4>
    <h4>status : ${element.status} </h4>
    <h4>Amount : ${element.paymentMoney} </h4>
    <h4>Booking Id : ${element.bookingId} </h4>
    <h4>Customer Id : ${element.customerId} </h4>
    </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });
  bid.append(div);
}

function addPackage() {
  let auth = Authentication();
  let hotelId;
  let destinationId;
  let packageName;
  let hotelType;
  let daysAndNight;
  let packageSeason;
  let packageDescription1;
  let packageDescription2;
  let packageDescription3;
  let packageDescription4;
  let packageDescription5;
  let packageCost;
  let div = document.createElement("div");
  bid.innerHTML = null;
  div.innerHTML = `<h2>Enter Details</h2>
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
  <form id="frm" action="javascript:void(0);">

  <div class="inputElement">
      <label for="hid"><h4>Enter the hotel Id </h4></label> <br>
      <input type="text" name="enviroment" id="hid" />
    </div>

    <div class="inputElement">
      <label for="did"><h4>Enter the destination Id </h4></label> <br>
      <input type="text" name="enviroment" id="did" />
    </div>

    <div class="inputElement">
      <label for="pn"><h4>Enter package name </h4></label> <br>
      <input type="text" name="enviroment" id="pn" />
    </div>
    <div class="inputElement">
      <label for="ht"><h4>Enter hotel type</h4></label><br>
      <input type="text" name="env" id="ht" />
    </div>
   
    <div class="inputElement">
      <label for="tt"><h4>Enter day or night</h4></label><br>
      <input type="text" name="env" id="tt" />
    </div>

    <div class="inputElement">
      <label for="ps"><h4>Enter package session</h4></label><br>
      <input type="text" name="env" id="ps" />
    </div>

    <div class="inputElement">
      <label for="pd1"><h4>Enter package description 1</h4></label><br>
      <input type="text" name="env" id="pd1" />
    </div>

    <div class="inputElement">
      <label for="pd2"><h4>Enter package description 2</h4></label><br>
      <input type="text" name="env" id="pd2" />
    </div>

    <div class="inputElement">
      <label for="pd3"><h4>Enter package description 3</h4></label><br>
      <input type="text" name="env" id="pd3" />
    </div>

    <div class="inputElement">
      <label for="pd4"><h4>Enter package description 4</h4></label><br>
      <input type="text" name="env" id="pd4" />
    </div>

    <div class="inputElement">
      <label for="pd5"><h4>Enter package description 5</h4></label><br>
      <input type="text" name="env" id="pd5" />
    </div>

    <div class="inputElement">
    <label for="pc"><h4>Enter package cost</h4></label><br>
    <input type="text" name="env" id="pc" />
  </div>

  </form>
  <input id="sb" type="submit" name="Login" class="sub" />`;
  div.setAttribute("class", "form");
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    hotelId = document.getElementById("hid").value;
    destinationId = document.getElementById("did").value;
    packageName = document.getElementById("pn").value;
    hotelType = document.getElementById("ht").value;
    daysAndNight = document.getElementById("tt").value;
    packageSeason = document.getElementById("ps").value;
    packageDescription1 = document.getElementById("pd1").value;
    packageDescription2 = document.getElementById("pd2").value;
    packageDescription3 = document.getElementById("pd3").value;
    packageDescription4 = document.getElementById("pd4").value;
    packageDescription5 = document.getElementById("pd5").value;
    packageCost = document.getElementById("pc").value;
    // bid.removeChild(div);
    fetch(
      "http://localhost:8080/travel/Packages/" + hotelId + "/" + destinationId,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Basic ${auth}`,
        },
        body: JSON.stringify({
          packageName: packageName,
          hotelType: hotelType,
          daysAndNight: daysAndNight,
          packageSeason: packageSeason,
          packageDescription1: packageDescription1,
          packageDescription2: packageDescription2,
          packageDescription3: packageDescription3,
          packageDescription4: packageDescription4,
          packageDescription5: packageDescription5,
          packageCost: packageCost,
        }),
      }
    )
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Unable to add package in deleted hotel") {
          notify(dat.message);
        } else if (
          dat.message == "Unable to add package in deleted destination"
        ) {
          notify(dat.message);
        } else if (dat.message == "Hotel not found with the given id") {
          notify(dat.message);
        } else if (dat.packageId != undefined) {
          getData31(dat.packageId);
        } else {
          window.location.assign("admin.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData31(dat) {
  notify("Package added successfully with package id " + dat);
}

function searchPackage() {
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    pid = document.getElementById("pid").value;
    // bid.removeChild(div);
    if (pid == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/Packages/" + pid, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Package is not exist now") {
          notify(dat.message);
        } else if (dat.message == "No package is found to view") {
          notify(dat.message);
        } else {
          getData32(dat);
        }
      })
      .catch((error) => console.log(error));
  });
}
function getData32(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  div.innerHTML = `<div class="busentry" >
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
     </div>`;
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });
  bid.append(div);
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
        getData33(dat);
      }
    })
    .catch((error) => console.log(error));
}
function getData33(data) {
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
    window.location.assign("admin.html");
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
    window.location.assign("admin.html");
  });

  document.getElementById("sb").addEventListener("click", () => {
    pid = document.getElementById("pid").value;
    // bid.removeChild(div);
    if (pid == "") window.location.assign("admin.html");
    fetch("http://localhost:8080/travel/Packages/" + pid, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => data.json())
      .then((dat) => {
        if (dat.message == "Unable to remove already deleted package") {
          notify(dat.message);
        } else if (dat.message == "No package is found to remove") {
          notify(dat.message);
        } else if (dat.packageId != undefined) {
          getData34(dat.packageId);
        } else window.location.assign("admin.html");
      })
      .catch((error) => console.log(error));
  });
}
function getData34(result) {
  console.log(result);
  notify("Package removed successfully with package id " + result);
}

function viewAllPackageBooking() {
  let auth = Authentication();
  bid.innerHTML = null;

  fetch("http://localhost:8080/travel/packages", {
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
      if (dat.message == "Booking list is empty") {
        notify(dat.message);
      } else {
        getData35(dat);
      }
    })
    .catch((error) => console.log(error));
}
function getData35(result) {
  console.log(result);
  bid.innerHTML = null;
  let div = document.createElement("div");
  let arr = [];
  result.forEach((element) => {
    arr.push(`<div class="busentry" >
    <h4>Booking Id : ${element.bookingId} </h4>
    <h4>Booking type : ${element.bookingType}</h4>
    <h4>Description : ${element.description}</h4>
    <h4>Status : ${element.status} </h4>
    <h4>Booking title : ${element.bookingTitle} </h4>
    <h4>Booking date : ${element.bookingDate} </h4>
    <h4>Package name : ${element.packageName} </h4>
    <h4>Numbe of person : ${element.number_Of_Person} </h4>
    <h4>Package Id : ${element.apackage.packageId} </h4>
    </div>`);
  });
  div.innerHTML = arr.join("");
  div.setAttribute("class", "grid-1");
  bid.innerHTML = `<i id="cb" class="fa-solid fa-circle-xmark"></i>`;
  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    // bid.removeChild(div);
    window.location.assign("admin.html");
  });
  bid.append(div);
}
