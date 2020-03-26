var estring = "I'm learning JavaScript in my web development module.";
var ecount = 0;
for (var i = 0; i < estring.length; i++) {
	if (estring.charAt(i) == 'e') {
		ecount++;
	}
}
console.log(ecount);
var friends = ["Tom", "Tim", "Lyn", "Bob"];
friends[3] = "Alex";
console.log(friends);
friends.splice(1,2);
console.log(friends);
friends.push("Dave", "Kelly");
console.log(friends);
var friendtemp = friends.pop();
friends.splice(0,0,friendtemp);
console.log(friends);
var ages = [56, 23, 45, 21, 2, 67, 34];
ages.sort();
console.log(ages);
ages.sort(function(a, b){return a-b});
console.log(ages);
ages.sort(function(a, b){return b-a});
console.log(ages);



