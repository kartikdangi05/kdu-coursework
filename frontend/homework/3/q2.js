const convert = (days) => {
    let result = [];
    days.forEach(element => {
        result.push(element.substring(0,3).toUpperCase());
    });
    
    return result;
}

const days = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
const result = convert(days);
console.log(result);

