const input1 = 'level';
const input2 = 'hello';
const input3 = 'radar';

function isPalindrome(s) {
    for (let i = 0; i < s.length / 2; i++) {
        if (s[i] !== s[s.length - 1 - i]) {
            return false;
        }
    }
    return true;
}

// big0 = n
console.log(isPalindrome(input1));
console.log(isPalindrome(input2));
console.log(isPalindrome(input3));

const nums1 = [2, 7, 11, 15]
const target1 = 9
const nums2 = [3, 2, 4, 3]
const target2 = 6

function twoSum(nums, target) {
    let results = [];
    for (let i = 0; i < nums.length - 1; i++) {
        for (let j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] === target) {
                results.push([i, j]);
            }
        }
    }
    return results;
}

// big0 = n^2
console.log(twoSum(nums1, target1));
console.log(twoSum(nums2, target2));

function twoSum2(nums, target) {
    const map = {};
    const results = [];

    for (let i = 0; i < nums.length; i++) {
        const complement = target - nums[i];

        if (complement in map) {
            results.push([map[complement], i]);
        }

        map[nums[i]] = i;
    }

    return results;
}

// big0 = n
console.log(twoSum2(nums1, target1)); // Output: [[0, 1]]
console.log(twoSum2(nums2, target2)); // Output: [[1, 2], [0, 3]]

// const fs = require('fs');
// const zlib = require('zlib');

// fs.readFile('data.json', 'utf8', (err, data) => {
//     if (err) throw err;
//
//     const minifiedData = JSON.stringify(JSON.parse(data));  // Bỏ khoảng trắng
//     zlib.brotliCompress(minifiedData, (err, result) => {    // Nén với Brotli
//         if (err) throw err;
//         fs.writeFileSync('data.min.json.br', result);
//         console.log('Tệp JSON đã được nén bằng Brotli thành công.');
//     });
// });
