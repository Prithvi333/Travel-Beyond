let pops = document.getElementById("pop");
let inp = document.getElementById("inp");

function openpopup() {
  pops.classList.add("open-pop");
}
function closepopup() {
  pops.classList.remove("open-pop");
}
let bid = document.getElementById("mc");
function signIn() {
  // bid.innerHTML = null;
  let div = document.createElement("div");

  div.innerHTML = `
  <h2>Welcome to login form</h2>
  <form id="frm" action="javascript:void(0);">
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
    <div class="inputElement">
      <label for="userName"><h4>Enter the user name</h4></label> <br>
      <input type="text" name="user" id="userName" />
    </div>
    <div class="inputElement">
      <label for="userPass"><h4>Enter the user password</h4></label><br>
      <input type="text" name="env" id="userPass" />
    </div>

    <input id="sb" type="submit" name="Login" class="sub" />
  </form>`;
  div.setAttribute("class", "form");

  bid.append(div);
  document.getElementById("cb").addEventListener("click", () => {
    bid.removeChild(div);
  });

  let forButton = document.getElementById("sb");
  forButton.addEventListener("click", () => {
    console.log("Its working..");
    let user = document.getElementById("userName").value;
    let password = document.getElementById("userPass").value;

    let auth = btoa(`${user}:${password}`);

    fetch("http://localhost:8080/travel/cusLogin", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => {
        console.log(data);
        if (data.ok == false) alert("Verification failed");
        else return data.json();
      })
      .then((res) => {
        // if (res.role == "ROLE_ADMIN") window.location.assign("admin.html");
        // else if (res.role == "ROLE_USER") window.location.assign("user.html");
      })
      .catch((error) => console.log(error));
  });
}
