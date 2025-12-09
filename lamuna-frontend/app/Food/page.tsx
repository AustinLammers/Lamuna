"use client";

import axios from "axios";
import { useEffect, useState } from "react";
import DateSwitcher from "./components/DateSwitcher";
import AddFoodForm from "./components/AddFoodForm";

type FoodItem = {
  id: number;
  name: string;
  calories?: number;
  parentLogId?: number | null;
  description?: string;
  protein?: number;
  carbs?: number;
  fat?: number;
  ingredients?: IngredientBreakdown[];
};

type IngredientBreakdown = {
  name: string;
  description?: string;
  calories?: number;
  protein?: number;
  carbs?: number;
  fat?: number;
};

const Food = () => {
  const todayString = new Date().toISOString().slice(0, 10);
  const [selectedDate, setSelectedDate] = useState<string>(todayString);
  const [foods, setFoods] = useState<FoodItem[]>([]);
  const [newFoodName, setNewFoodName] = useState<string>("");
  const [newFoodCalories, setNewFoodCalories] = useState<string>("");
  const [newFoodCategory, setNewFoodCategory] = useState<string>("breakfast");
  const [newFoodProtein, setNewFoodProtein] = useState<string>("");
  const [newFoodCarbs, setNewFoodCarbs] = useState<string>("");
  const [newFoodFat, setNewFoodFat] = useState<string>("");
  const [newFoodDescription, setNewFoodDescription] = useState<string>("");
  const [selectedFood, setSelectedFood] = useState<IngredientBreakdown | null>(
    null,
  );

  const formatDate = (date: Date) => {
    return date.toISOString().slice(0, 10);
  };

  const goToPreviousDay = () => {
    const current = new Date(selectedDate);
    current.setDate(current.getDate() - 1);
    setSelectedDate(formatDate(current));
  };

  const goToNextDay = () => {
    const current = new Date(selectedDate);
    current.setDate(current.getDate() + 1);
    const nextDay = formatDate(current);

    if (nextDay > todayString) {
      return;
    }

    setSelectedDate(nextDay);
  };

  useEffect(() => {
    loadFoods(selectedDate);
  }, [selectedDate]);

  const loadFoods = async (dateString: string) => {
    const response = await axios.get<FoodItem[]>(
      "http://localhost:8080/api/foods",
      {
        params: { date: dateString },
      },
    );

    setFoods(response.data || []);
  };

  const getCategoryFoods = (category: string) => {
    const matchName = category.toLowerCase();
    const root = foods.find((item) => {
      const itemName = item.name ? item.name.toLowerCase() : "";
      const isRoot =
        item.parentLogId === null || item.parentLogId === undefined;
      return itemName === matchName && isRoot;
    });

    if (!root) {
      return [];
    }

    if (root.ingredients && root.ingredients.length > 0) {
      return root.ingredients.slice(1);
    }

    return [];
  };

  const ensureCategory = async (category: string) => {
    const lower = category.toLowerCase();
    const existing = foods.find((item) => {
      const itemName = item.name ? item.name.toLowerCase() : "";
      const isRoot =
        item.parentLogId === null || item.parentLogId === undefined;
      return itemName === lower && isRoot;
    });

    if (existing) {
      return existing.id;
    }

    const createResponse = await axios.post<FoodItem>(
      "http://localhost:8080/api/foods",
      {
        name: category,
        description: "",
        date: selectedDate,
        calories: 0,
        protein: 0,
        carbs: 0,
        fat: 0,
        userId: 1,
        parentLogId: null,
      },
    );

    return createResponse.data.id;
  };

  const handleAddFood = async (event: React.FormEvent) => {
    event.preventDefault();

    const parentId = await ensureCategory(newFoodCategory);

    const caloriesNumber = parseInt(newFoodCalories || "0", 10) || 0;
    const proteinNumber = parseFloat(newFoodProtein || "0") || 0;
    const carbsNumber = parseFloat(newFoodCarbs || "0") || 0;
    const fatNumber = parseFloat(newFoodFat || "0") || 0;

    await axios.post("http://localhost:8080/api/foods", {
      name: newFoodName,
      description: newFoodDescription,
      date: selectedDate,
      calories: caloriesNumber,
      protein: proteinNumber,
      carbs: carbsNumber,
      fat: fatNumber,
      userId: 1,
      parentLogId: parentId,
    });

    setNewFoodName("");
    setNewFoodCalories("");
    setNewFoodProtein("");
    setNewFoodCarbs("");
    setNewFoodFat("");
    setNewFoodDescription("");

    await loadFoods(selectedDate);
  };

  return (
    <div className="flex flex-col items-center min-h-screen bg-white">
      <div className="flex w-full max-w-4xl flex-col gap-y-6 px-6 py-10">
        <DateSwitcher
          selectedDate={selectedDate}
          todayString={todayString}
          onPrev={goToPreviousDay}
          onNext={goToNextDay}
        />
        <div className="w-full rounded-lg border border-gray-200 bg-white p-4 shadow-sm">
          <div className="mb-3 text-lg font-bold text-gray-900 text-center">
            Meals for{" "}
            {new Date(selectedDate).toLocaleDateString(undefined, {
              year: "numeric",
              month: "long",
              day: "numeric",
              timeZone: "UTC",
            })}
          </div>
          <div className="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-2">
            <div className="rounded-md border border-gray-100">
              <div className="rounded-t-lg bg-yellow-200 px-4 py-2 text-sm font-semibold text-gray-900">
                Breakfast
              </div>
              <div className="h-48 overflow-y-auto px-3 py-2 space-y-2 text-sm text-gray-800">
                <div className="grid grid-cols-5 gap-2 font-semibold text-gray-900">
                  <span>Name</span>
                  <span className="text-right">Calories</span>
                  <span className="text-right">Protein</span>
                  <span className="text-right">Carbs</span>
                  <span className="text-right">Fat</span>
                </div>
                {getCategoryFoods("breakfast").map((item, index) => (
                  <button
                    key={`${item.name}-${index}`}
                    className="grid w-full grid-cols-5 gap-2 text-left hover:bg-gray-100 rounded px-1"
                    type="button"
                    onClick={() => setSelectedFood(item)}
                  >
                    <span className="truncate">{item.name}</span>
                    <span className="text-right">{item.calories || 0}</span>
                    <span className="text-right">{item.protein || 0}</span>
                    <span className="text-right">{item.carbs || 0}</span>
                    <span className="text-right">{item.fat || 0}</span>
                  </button>
                ))}
              </div>
            </div>
            <div className="rounded-md border border-gray-100">
              <div className="rounded-t-lg bg-red-200 px-4 py-2 text-sm font-semibold text-gray-900">
                Lunch
              </div>
              <div className="h-48 overflow-y-auto px-3 py-2 space-y-2 text-sm text-gray-800">
                <div className="grid grid-cols-5 gap-2 font-semibold text-gray-900">
                  <span>Name</span>
                  <span className="text-right">Calories</span>
                  <span className="text-right">Protein</span>
                  <span className="text-right">Carbs</span>
                  <span className="text-right">Fat</span>
                </div>
                {getCategoryFoods("lunch").map((item, index) => (
                  <button
                    key={`${item.name}-${index}`}
                    className="grid w-full grid-cols-5 gap-2 text-left hover:bg-gray-100 rounded px-1"
                    type="button"
                    onClick={() => setSelectedFood(item)}
                  >
                    <span className="truncate">{item.name}</span>
                    <span className="text-right">{item.calories || 0}</span>
                    <span className="text-right">{item.protein || 0}</span>
                    <span className="text-right">{item.carbs || 0}</span>
                    <span className="text-right">{item.fat || 0}</span>
                  </button>
                ))}
              </div>
            </div>
            <div className="rounded-md border border-gray-100">
              <div className="rounded-t-lg bg-blue-900 px-4 py-2 text-sm font-semibold text-white">
                Dinner
              </div>
              <div className="h-48 overflow-y-auto px-3 py-2 space-y-2 text-sm text-gray-800">
                <div className="grid grid-cols-5 gap-2 font-semibold text-gray-900">
                  <span>Name</span>
                  <span className="text-right">Calories</span>
                  <span className="text-right">Protein</span>
                  <span className="text-right">Carbs</span>
                  <span className="text-right">Fat</span>
                </div>
                {getCategoryFoods("dinner").map((item, index) => (
                  <button
                    key={`${item.name}-${index}`}
                    className="grid w-full grid-cols-5 gap-2 text-left hover:bg-gray-100 rounded px-1"
                    type="button"
                    onClick={() => setSelectedFood(item)}
                  >
                    <span className="truncate">{item.name}</span>
                    <span className="text-right">{item.calories || 0}</span>
                    <span className="text-right">{item.protein || 0}</span>
                    <span className="text-right">{item.carbs || 0}</span>
                    <span className="text-right">{item.fat || 0}</span>
                  </button>
                ))}
              </div>
            </div>
            <div className="rounded-md border border-gray-100">
              <div className="rounded-t-lg bg-green-200 px-4 py-2 text-sm font-semibold text-gray-900">
                Snack
              </div>
              <div className="h-48 overflow-y-auto px-3 py-2 space-y-2 text-sm text-gray-800">
                <div className="grid grid-cols-5 gap-2 font-semibold text-gray-900">
                  <span>Name</span>
                  <span className="text-right">Calories</span>
                  <span className="text-right">Protein</span>
                  <span className="text-right">Carbs</span>
                  <span className="text-right">Fat</span>
                </div>
                {getCategoryFoods("snack").map((item, index) => (
                  <button
                    key={`${item.name}-${index}`}
                    className="grid w-full grid-cols-5 gap-2 text-left hover:bg-gray-100 rounded px-1"
                    type="button"
                    onClick={() => setSelectedFood(item)}
                  >
                    <span className="truncate">{item.name}</span>
                    <span className="text-right">{item.calories || 0}</span>
                    <span className="text-right">{item.protein || 0}</span>
                    <span className="text-right">{item.carbs || 0}</span>
                    <span className="text-right">{item.fat || 0}</span>
                  </button>
                ))}
              </div>
            </div>
          </div>
          <AddFoodForm
            newFoodName={newFoodName}
            newFoodCalories={newFoodCalories}
            newFoodProtein={newFoodProtein}
            newFoodCarbs={newFoodCarbs}
            newFoodFat={newFoodFat}
            newFoodDescription={newFoodDescription}
            newFoodCategory={newFoodCategory}
            onNameChange={setNewFoodName}
            onCaloriesChange={setNewFoodCalories}
            onProteinChange={setNewFoodProtein}
            onCarbsChange={setNewFoodCarbs}
            onFatChange={setNewFoodFat}
            onDescriptionChange={setNewFoodDescription}
            onCategoryChange={setNewFoodCategory}
            onSubmit={handleAddFood}
          />
        </div>
      </div>
      {selectedFood ? (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50 px-4">
          <div className="w-full max-w-md rounded-lg bg-white p-5 shadow-lg space-y-3">
            <div className="flex items-center justify-between">
              <div className="text-lg font-bold text-gray-900">
                {selectedFood.name}
              </div>
              <div className="flex items-center gap-2">
                <button
                  type="button"
                  onClick={async () => {
                    await axios.delete(
                      `http://localhost:8080/api/foods/${selectedFood?.id}`,
                    );
                    setSelectedFood(null);
                    loadFoods(selectedDate);
                  }}
                  className="rounded bg-red-600 px-3 py-1 text-xs font-semibold text-white hover:bg-red-700"
                >
                  Delete
                </button>
                <button
                  type="button"
                  className="text-sm font-semibold text-gray-700 hover:underline"
                  onClick={() => setSelectedFood(null)}
                >
                  Close
                </button>
              </div>
            </div>
            {selectedFood.description ? (
              <div className="text-sm text-gray-800">
                {selectedFood.description}
              </div>
            ) : null}
            {selectedFood.ingredients && selectedFood.ingredients.length > 1 ? (
              <div className="space-y-2">
                <div className="text-sm font-semibold text-gray-900">
                  Ingredients
                </div>
                <div className="space-y-1 text-sm text-gray-800">
                  {selectedFood.ingredients.slice(1).map((ing, idx) => (
                    <div
                      key={`${ing.name}-${idx}`}
                      className="flex justify-between"
                    >
                      <span>{ing.name}</span>
                      <span>{ing.calories || 0} cal</span>
                    </div>
                  ))}
                </div>
              </div>
            ) : null}
          </div>
        </div>
      ) : null}
    </div>
  );
};

export default Food;
