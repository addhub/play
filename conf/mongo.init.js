/**
 * Created by sasinda on 10/1/15.
 */

//use addhub

db.category.insert({
                     "name": "Vehicle",
                     "collection":"ad_",
                     "subcats": [
                       "Automotive Items & Parts",
                       "Boats & Watercraft",
                       "Cars",
                       "Classic Cars",
                       "Commercial Trucks & Tractor Trailers",
                       "Off Road Vehicles",
                       "RV & Motorhomes",
                       "SUVs",
                       "Utility & Work Trailers",
                       "Vans"
                     ]
                   });
db.category.insert({
                     "name": "Services",
                     "subcats": [
                       "Automotive Services",
                       "Beauty & Salon Services",
                       "Caregivers & Baby Sitting",
                       "Cleaning Services",
                       "Construction & Remodeling",
                       "Financial Services",
                       "Health & Wellness",
                       "Home Services",
                       "Insurance",
                       "Office Services",
                       "Real Estate Services"
                     ]
                   });
db.category.insert({
                     "name": "Pets",
                     "subcats": [
                       "Birds",
                       "Cats",
                       "Dogs",
                       "Fish & Reptile Pets",
                       "Free Pets to Good Home",
                       "Horses",
                       "Pet Supplies"
                     ]
                   });




//Insert Test objects
db.Vehicle.insert({
    "_id" : ObjectId("560f0841e746b7031b037829"),
    "title" : "Title",
    "category":"Vehicle",
    "subCat" : "Cars",
    "description" : "Gallant",
    "keywords" : ["Gallant","Diesel"],
    "address" : "309 West 30th Street",
    "state" : "NY",
    "country" : "USA",
    "zipcode" : "10001",
    "price" : 100000,
    "agree" : true
});

db.Vehicle.insert({
    "_id" : ObjectId("56310b651792c62cd39d4f03"),
    "id" : null,
    "title" : "Toyota Camry For Sale",
    "category" : "Vehicle",
    "subCat" : "Cars",
    "description" : "My Toyota Camry :)",
    "keywords" : "#Camry #FuelEfficient",
    "address" : "309 West 30th Street",
    "state" : "NY",
    "country" : "United States",
    "zipcode" : "10001",
    "price" : 10000.0000000000000000,
    "pictureUrls" :[ "http://shebuyscars.com/wp-content/uploads/2014/10/2014-Camry-Hybrid-Ventura-Toyota.jpg"],
    "agree" : true
});


db.Vehicle.insert({
    "_id" : ObjectId("56312d705ca7750239c293eb"),
    "id" : null,
    "title" : "Honda Civic 2000",
    "category" : "Vehicle",
    "subCat" : "Cars",
    "description" : "It's really fast",
    "keywords" : "car",
    "address" : "my house",
    "state" : "NY",
    "country" : "USA",
    "zipcode" : "11109",
    "price" : 99999,
    "agree" : false,
    "pictureUrls" : "http://i.ytimg.com/vi/CMHH5T0My6k/maxresdefault.jpg"
});