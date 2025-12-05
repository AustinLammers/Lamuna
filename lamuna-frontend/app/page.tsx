"use client";

import axios from "axios";
import { useEffect, useState } from "react";

type FoodItem = {
  id: number;
  name: string;
  description?: string;
  calories?: number;
  protein?: number;
  carbs?: number;
  fat?: number;
};

const Home = () => {
  const [foods, setFoods] = useState<FoodItem[]>([]);

  useEffect(() => {
    const getFood = async () => {
      const res = await axios.get("http://localhost:8080/api/foods");
      setFoods(res.data);
    };

    getFood();
  }, []);

  return (
    <div className="flex flex-col items-center min-h-screen bg-white">
      <div className="flex bg-teal-700 w-full justify-center items-center py-20">
        <h1 className="text-5xl text-center text-white font-bold">
          Lamuna Health Tracker
        </h1>
      </div>
      <ul className="mt-8 space-y-2">
        {foods.map((item) => (
          <li key={item.id} className="border p-4 rounded">
            <div className="font-semibold">{item.name}</div>
            <div className="text-sm text-gray-600">{item.description}</div>
            <div className="text-sm">
              Calories: {item.calories ?? 0} | Protein: {item.protein ?? 0} |
              Carbs: {item.carbs ?? 0} | Fat: {item.fat ?? 0}
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Home;
