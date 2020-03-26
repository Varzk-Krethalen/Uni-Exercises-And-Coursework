var a = 3;
var b = 5;
console.log(multiply(a,b));
console.log(square(a));
var full = "dwsgfdgfdbfdbfdbvdcger";
var search = "db";
console.log(countStr(full,search));
var array = [12,74,347,83,1234];
console.log(rotate(array,a));
document.getElementById("q6").addEventListener('click', buttonClick);
document.getElementById("q8").addEventListener('click', buttonClick);



function multiply(a,b)
{
	return (a*b);
}
function square(a)
{
	return(a*a);
}
function countStr(full,search)
{
	var count = 0;
	var temp;
	while(full.indexOf(search) >= 0)
	{
		temp = full.indexOf(search);
		temp += search.length;
		full = full.substr(temp);
		count++
	}
	return count;
}
function rotate(array,iteration)
{
	for (var i = 0; i < iteration; i++)
	{
		temp = array.pop();
		array.splice(0,0,temp);
	}
	return array;
}
function buttonClick()
{
	event.preventDefault();
	console.log("Clicked");
	return;
}