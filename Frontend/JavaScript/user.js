let pops = document.getElementById("pop");

function openpopup() {
  if (pops.innerHTML != null) pops.classList.add("open-pop");
}
function closepopup() {
  pops.classList.remove("open-pop");
}
