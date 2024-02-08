const stringObj = '{"firstName":"Alex","lastName":"Hunter","email":"alex@yahoo.com","age":24, "city":"london", "country":"england"}';

const jsonObj = JSON.parse(stringObj);

jsonObj["firstName"] = jsonObj.firstName.toUpperCase();
jsonObj["lastName"] = jsonObj.lastName.toUpperCase();
jsonObj["city"] = jsonObj.city.toUpperCase();
jsonObj["country"] = jsonObj.country.toUpperCase();

console.log(jsonObj);
