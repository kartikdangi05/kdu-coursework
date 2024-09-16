const tipCalculator = (bill) => {
    let tips = [];
    let finalBill = [];

    bill.forEach(element => {
        if(element < 50){
            tips.push(0.2 * element);
            finalBill.push(1.2 * element)
        }
        else if(element >= 50 && element <= 200){
            tips.push(0.15 * element);
            finalBill.push(1.15 * element)
        }
        else{
            tips.push(0.10 * element);
            finalBill.push(1.10 * element);
        }
    });

    return {tips,finalBill};
}

const bill = [140,45,280];
const {tips, finalBill} = tipCalculator(bill);
console.log("Tips : ",tips);
console.log("Final bill : ",finalBill);