const shoes = [
    { type: "sneaker", color: "black", size: 10, price: 80 },
    { type: "loafer", color: "blue", size: 9, price: 100 }
];

const shirts = [
    { type: "polo", color: "blue", size: "M", price: 35 },
    { type: "dress shirt", color: "white", size: "L", price: 45 },
    { type: "t-shirt", color: "red", size: "S", price: 25 }
];

const warehouse = [...shirts,...shoes];
console.log("Warehouse : ",warehouse);

let totalPrice = 0;
warehouse.forEach((warehouseObj) => {
    totalPrice += warehouseObj.price;
});
console.log("Total price : ", totalPrice);

warehouse.sort((a,b) => b.price - a.price);
console.log("Sorted warehouse : ",warehouse);


const blueItems = warehouse.filter(item => item.color === "blue");
console.log("Blue items:", blueItems);





