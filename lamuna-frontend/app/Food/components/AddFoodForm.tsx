"use client";

type AddFoodFormProps = {
  newFoodName: string;
  newFoodCalories: string;
  newFoodProtein: string;
  newFoodCarbs: string;
  newFoodFat: string;
  newFoodDescription: string;
  newFoodCategory: string;
  onNameChange: (value: string) => void;
  onCaloriesChange: (value: string) => void;
  onProteinChange: (value: string) => void;
  onCarbsChange: (value: string) => void;
  onFatChange: (value: string) => void;
  onDescriptionChange: (value: string) => void;
  onCategoryChange: (value: string) => void;
  onSubmit: (event: React.FormEvent) => void;
};

const AddFoodForm = ({
  newFoodName,
  newFoodCalories,
  newFoodProtein,
  newFoodCarbs,
  newFoodFat,
  newFoodDescription,
  newFoodCategory,
  onNameChange,
  onCaloriesChange,
  onProteinChange,
  onCarbsChange,
  onFatChange,
  onDescriptionChange,
  onCategoryChange,
  onSubmit,
}: AddFoodFormProps) => {
  return (
    <div className="mt-6 rounded-lg border border-gray-200 bg-white p-4 shadow-sm">
      <div className="mb-3 text-lg font-bold text-gray-900">Add Food</div>
      <form className="flex flex-col gap-3" onSubmit={onSubmit}>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">
            Food Name
          </label>
          <input
            type="text"
            required
            value={newFoodName}
            onChange={(e) => onNameChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          />
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">
            Description
          </label>
          <input
            type="text"
            value={newFoodDescription}
            onChange={(e) => onDescriptionChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          />
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">
            Calories
          </label>
          <input
            type="number"
            min="0"
            required
            value={newFoodCalories}
            onChange={(e) => onCaloriesChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          />
        </div>
        <div className="grid grid-cols-1 gap-3 md:grid-cols-3">
          <div className="flex flex-col gap-1">
            <label className="text-sm font-semibold text-gray-800">
              Protein
            </label>
            <input
              type="number"
              min="0"
              required
              value={newFoodProtein}
              onChange={(e) => onProteinChange(e.target.value)}
              className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
              placeholder="g"
            />
          </div>
          <div className="flex flex-col gap-1">
            <label className="text-sm font-semibold text-gray-800">Carbs</label>
            <input
              type="number"
              min="0"
              required
              value={newFoodCarbs}
              onChange={(e) => onCarbsChange(e.target.value)}
              className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
              placeholder="g"
            />
          </div>
          <div className="flex flex-col gap-1">
            <label className="text-sm font-semibold text-gray-800">Fat</label>
            <input
              type="number"
              min="0"
              required
              value={newFoodFat}
              onChange={(e) => onFatChange(e.target.value)}
              className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
              placeholder="g"
            />
          </div>
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-semibold text-gray-800">
            Category
          </label>
          <select
            value={newFoodCategory}
            onChange={(e) => onCategoryChange(e.target.value)}
            className="rounded border border-gray-300 px-3 py-2 text-sm text-gray-900 focus:border-teal-600 focus:outline-none focus:ring-1 focus:ring-teal-600"
          >
            <option value="breakfast">Breakfast</option>
            <option value="lunch">Lunch</option>
            <option value="dinner">Dinner</option>
            <option value="snack">Snack</option>
          </select>
        </div>
        <button
          type="submit"
          className="mt-2 rounded bg-teal-700 px-4 py-2 text-sm font-semibold text-white hover:bg-teal-800"
        >
          Add Food
        </button>
      </form>
    </div>
  );
};

export default AddFoodForm;
