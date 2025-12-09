"use client";

import axios from "axios";
import { useEffect, useState } from "react";

type FoodIngredient = {
  name: string;
  description?: string;
  calories?: number;
  protein?: number;
  carbs?: number;
  fat?: number;
};

type FoodItem = {
  id: number;
  name: string;
  description?: string;
  date?: string;
  userId?: number;
  parentLogId?: number | null;
  calories?: number;
  protein?: number;
  carbs?: number;
  fat?: number;
  ingredients?: FoodIngredient[];
};

type MacroTotals = {
  calories: number;
  protein: number;
  carbs: number;
  fat: number;
};

type WorkoutItem = {
  id: number;
  name: string;
  type?: string;
  calories?: number;
  minutes?: number;
  sets?: number;
  reps?: number;
  date?: string;
};

const Home = () => {
  const today = new Date().toISOString().slice(0, 10);
  const [foods, setFoods] = useState<FoodItem[]>([]);
  const [workouts, setWorkouts] = useState<WorkoutItem[]>([]);

  useEffect(() => {
    const getFood = async () => {
      const response = await axios.get<FoodItem[]>(
        "http://localhost:8080/api/foods",
        {
          params: { date: today },
        },
      );

      setFoods(response.data);
    };

    getFood();
  }, []);

  useEffect(() => {
    const getWorkouts = async () => {
      const response = await axios.get<WorkoutItem[]>(
        "http://localhost:8080/api/workouts",
        {
          params: { date: today },
        },
      );

      setWorkouts(response.data || []);
    };

    getWorkouts();
  }, []);

  const getCategoryTotals = (category: string): MacroTotals => {
    const totals: MacroTotals = { calories: 0, protein: 0, carbs: 0, fat: 0 };

    for (let i = 0; i < foods.length; i++) {
      const item = foods[i];
      const itemName = item.name ? item.name.toLowerCase() : "";

      if (itemName === category) {
        totals.calories += item.calories || 0;
        totals.protein += item.protein || 0;
        totals.carbs += item.carbs || 0;
        totals.fat += item.fat || 0;
      }
    }

    return totals;
  };

  const workoutTotals = workouts.reduce(
    (acc, item) => {
      acc.count += 1;
      acc.calories += item.calories || 0;
      acc.minutes += item.minutes || 0;
      acc.sets += item.sets || 0;
      acc.reps += item.reps || 0;
      return acc;
    },
    { count: 0, calories: 0, minutes: 0, sets: 0, reps: 0 },
  );

  return (
    <div className="flex flex-col items-center gap-y-14 min-h-screen bg-white">
      <div className="flex bg-teal-700 w-full justify-center items-center py-10">
        <h1 className="text-5xl text-center text-white font-bold">
          Lamuna Health Tracker
        </h1>
      </div>
      <div className="flex justify-center items-center w-3/4 gap-x-10">
        {/*Food Summary*/}
        <div className="rounded-xl border border-gray-200 bg-white p-6 shadow-sm w-2/3 flex flex-col gap-y-4">
          <h2 className="text-2xl text-center font-bold text-gray-900">
            Today&apos;s Meals
          </h2>

          <div className="rounded-lg border border-gray-100">
            <div className="rounded-t-lg text-center bg-yellow-400 px-4 py-2 text-sm font-bold text-white">
              Breakfast
            </div>
            <div className="space-y-1 px-4 py-3 text-sm text-gray-800 text-center font-semibold">
              <div>Calories: {getCategoryTotals("breakfast").calories}</div>
              <div>Protein: {getCategoryTotals("breakfast").protein}</div>
              <div>Carbs: {getCategoryTotals("breakfast").carbs}</div>
              <div>Fat: {getCategoryTotals("breakfast").fat}</div>
            </div>
          </div>
          <div className="rounded-lg border border-gray-100">
            <div className="rounded-t-lg text-center bg-red-400 px-4 py-2 text-sm font-bold text-white">
              Lunch
            </div>
            <div className="space-y-1 px-4 py-3 text-sm text-gray-800 text-center font-semibold">
              <div>Calories: {getCategoryTotals("lunch").calories}</div>
              <div>Protein: {getCategoryTotals("lunch").protein}</div>
              <div>Carbs: {getCategoryTotals("lunch").carbs}</div>
              <div>Fat: {getCategoryTotals("lunch").fat}</div>
            </div>
          </div>
          <div className="rounded-lg border border-gray-100">
            <div className="rounded-t-lg text-center bg-blue-900 px-4 py-2 text-sm font-bold text-white">
              Dinner
            </div>
            <div className="space-y-1 px-4 py-3 text-sm text-gray-800 text-center font-semibold">
              <div>Calories: {getCategoryTotals("dinner").calories}</div>
              <div>Protein: {getCategoryTotals("dinner").protein}</div>
              <div>Carbs: {getCategoryTotals("dinner").carbs}</div>
              <div>Fat: {getCategoryTotals("dinner").fat}</div>
            </div>
          </div>
          <div className="rounded-lg border border-gray-100">
            <div className="rounded-t-lg text-center bg-green-400 px-4 py-2 text-sm font-bold text-white">
              Snacks
            </div>
            <div className="space-y-1 px-4 py-3 text-sm text-gray-800 text-center font-semibold">
              <div>Calories: {getCategoryTotals("snack").calories}</div>
              <div>Protein: {getCategoryTotals("snack").protein}</div>
              <div>Carbs: {getCategoryTotals("snack").carbs}</div>
              <div>Fat: {getCategoryTotals("snack").fat}</div>
            </div>
          </div>
        </div>

        {/*Workout Summary*/}
        <div className="rounded-xl border border-gray-200 bg-white p-6 shadow-sm w-2/3 flex flex-col gap-y-4">
          <h2 className="text-2xl text-center font-bold text-gray-900">
            Today&apos;s Workouts
          </h2>
          <div className="rounded-lg border border-gray-100">
            <div className="rounded-t-lg text-center bg-gray-800 px-4 py-2 text-sm font-bold text-white">
              Summary
            </div>
            <div className="space-y-1 px-4 py-3 text-lg text-gray-800 text-center font-semibold">
              <div>Workouts: {workoutTotals.count}</div>
              <div>Calories Burned: {workoutTotals.calories}</div>
              <div>Minutes: {workoutTotals.minutes}</div>
              <div>Reps: {workoutTotals.reps}</div>
              <div>Sets: {workoutTotals.sets}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
