import { Outlet } from "react-router-dom";
import Header from "../header";
import Sidebar from "../sidebar";
import Footer from "../footer";

function LayoutMain() {
  return (
    <div className="d-flex flex-column vh-100">
      <Header />
      <div className="d-flex flex-grow-1">
        <Sidebar />
        <main className="flex-grow-1 p-3 bg-light">
          <Outlet />
        </main>
      </div>
      <Footer />
    </div>
  );
}

export default LayoutMain;
