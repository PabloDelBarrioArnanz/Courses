
//Window methods / Objects /Properties

//Console
//console.log('Hello')

//Alert
//alert('Hello')

//Prompt
//const input = prompt()
//console.log(input)

//Confirm
//if (confirm('Are you sure?')) {
//    console.log('Yes')
//} else {
//    console.log('No')
//}

//Outter and inner height and width
const heightOuter = window.outerHeight
const widthOuter = window.outerWidth
const heightInner = window.innerHeight
const widthInner = window.innerWidth

console.log(`Window outer resolution: ${heightOuter} x ${widthOuter}`) //Browser window size
console.log(`Window inner resolution: ${heightInner} x ${widthInner}`) //Page window size (example outter - console windows size = inner)

//Scroll points
const scrollY = window.scrollY
const scrollX = window.scrollX
console.log(scrollY)

//Location object
const localization = window.location
const hostname = window.location.hostname
const address = window.location.address
const href = window.location.href
const search = window.location.search //queryParams ?q=1&name=pablo
console.log(localization)
console.log(search)

//Redirect
//window.location.href = "https://google.com"

//Reload
//window.location.reload() //Infinite loop

//History Object
//window.history.go()
//window.history.go(-10)

//Navigator Object
console.log(window.navigator.appName)
console.log(window.navigator.appVersion)
console.log(window.navigator.platform)
console.log(window.navigator.vendor)
console.log(window.navigator.languages)