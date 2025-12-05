import Link from "next/link";

const Header = () => {
  return (
    <div className="sticky left-0 top-0 z-40 bg-black">
      <header className="z-30 flex h-24 items-center justify-center gap-x-40">
        <Link href="/" className="text-lg font-bold text-white">
          Home
        </Link>
        <Link href="/Food" className="text-lg font-bold text-white">
          Food Log
        </Link>
        <Link href="/Workout" className="text-lg font-bold text-white">
          Workout Log
        </Link>
      </header>
    </div>
  );
};

export default Header;
