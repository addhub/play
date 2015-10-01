/**
 * Created by sasinda on 10/1/15.
 */

//use addhub

db.category.insert({
                     "name": "Vehicle",
                     "subcats": [
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
                     "subcategories": [
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
                     "subcategories": [
                       "Birds",
                       "Cats",
                       "Dogs",
                       "Fish & Reptile Pets",
                       "Free Pets to Good Home",
                       "Horses",
                       "Pet Supplies",
                       ""
                     ]
                   });


