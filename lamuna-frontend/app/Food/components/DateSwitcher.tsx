"use client";

type DateSwitcherProps = {
  selectedDate: string;
  todayString: string;
  onPrev: () => void;
  onNext: () => void;
};

const DateSwitcher = ({
  selectedDate,
  todayString,
  onPrev,
  onNext,
}: DateSwitcherProps) => {
  return (
    <div className="flex items-center justify-center gap-4">
      <button
        type="button"
        onClick={onPrev}
        className="rounded border border-gray-300 px-3 py-2 text-sm font-semibold text-gray-800 hover:bg-gray-100"
      >
        ←
      </button>
      <div className="text-lg font-bold text-gray-900">{selectedDate}</div>
      <button
        type="button"
        onClick={onNext}
        className={`rounded border border-gray-300 px-3 py-2 text-sm font-semibold text-gray-800 hover:bg-gray-100 ${selectedDate === todayString ? "opacity-50 cursor-not-allowed" : ""}`}
      >
        →
      </button>
    </div>
  );
};

export default DateSwitcher;
