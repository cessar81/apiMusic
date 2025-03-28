CREATE TABLE IF NOT EXISTS CARS_MOVIES_ENTITY (
                                    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
                                    car_movie_name VARCHAR(255) NOT NULL,
                                    car_movie_year VARCHAR(4) NOT NULL,
                                    duration INTEGER NOT NULL
);
