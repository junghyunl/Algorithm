const path = process.platform === 'linux' ? '/dev/stdin' : 'input.txt';
const input = require('fs').readFileSync(path).toString().trim().split('\n');

const [a,b,c] = input.map(Number);
console.log(a+b-c);
console.log(+(a.toString()+b.toString())-c);