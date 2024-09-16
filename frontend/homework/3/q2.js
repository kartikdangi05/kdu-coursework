const convert1 = (days) => {
    let result = [];
    days.forEach(element => {
        result.push(element.substring(0,3).toUpperCase());
    });
    
    return result;
}

const convert2 = (strings) => {
    let result = [];
    strings.forEach((element) => {
        let tempString = "";
        Array.from(element).forEach((character) => {
            if(character == 'a')    tempString += '4';
            else if(character == 'e')   tempString += '3';
            else if(character == 'i')   tempString += '1';
            else if(character == 'o')   tempString += '0';
            else if(character == 's')   tempString += '5';
            else    tempString += character;
        })
        result.push(tempString);
    })
    return result;
}

const days = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
const result1 = convert1(days);
console.log(result1);

const strings = ["javascript is cool","programming is fun","become a coder"];
const result2 = convert2(strings);
console.log(result2);
