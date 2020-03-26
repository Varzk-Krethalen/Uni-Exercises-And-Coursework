window.addEventListener("load", function(){
	var subform = document.getElementById("myform2");
	subform.addEventListener("submit", days());
})

function days(){
	var dayInput = document.getElementById("submit1").value.trim();
	var div2 = document.getElementById("div2");
	var day = "Not a valid day";
	if (dayInput.length > 0){
	dayInput = parseFloat(dayInput);
		if (!isNaN(dayInput)){
			switch (dayInput){
				case 1:
					day = "Monday";
					break;
				case 2:
					day = "Tuesday";
					break;
				case 3:
					day = "Wednesday";
					break;
				case 4:
					day = "Thursday";
					break;
				case 5:
					day = "Friday";
					break;
				case 6:
					day = "Saturday";
				case 7:
					day = "Sunday";
					break;
				default:
					day = "Not a valid day";
					break
			}
			div2.textContent = day;
		}
	}
	event.preventDefault();
}

function convert(){
	var fahr = document.getElementById("fdeg").value.trim();
	var cels = document.getElementById("cdeg");
	if (fahr.length > 0){
		fahr = parseFloat(fahr);
		if (!isNaN(fahr)){
			fahr = (fahr - 32)/1.8;
			cels.textContent = fahr;
		}
	}
}