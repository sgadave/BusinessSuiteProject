import FooterNavBar from "../common/FooterNavBar";
import "../../styles/HomePage.css";
import Image1 from "../../images/homepage/Image1.jpeg"
import Image2 from "../../images/homepage/Image2.jpeg"
import Image3 from "../../images/homepage/Image3.jpeg"
import { useState } from "react";


const HomePage = () => {
    const imageList = [
        {
            src: Image1,
            alt: "Image 1"
        },
        {
            src: Image2,
            alt: "Image 2"
        },
        {
            src: Image3,
            alt: "Image 3"
        }
    ];

    const [currentImageIndex, setCurrentImageIndex] = useState(0);

    const handleNext = () => {
        setCurrentImageIndex(currentImageIndex === imageList.length - 1 ? 0 : currentImageIndex + 1);
    }

    const handlePrev = () => {
        setCurrentImageIndex(currentImageIndex === 0 ? imageList.length - 1 : currentImageIndex - 1);
    }

    return (
        <div>
            
            <section>
                <div className="slider-container">
                    <div className="slider-image-container">
                        <img src={imageList[currentImageIndex].src} alt={imageList[currentImageIndex].alt} style={{ objectFit: 'cover', width: '100%', height: '100%' }} />
                    </div>
                    <div className="slider-button-container">
                        <button onClick={handlePrev} className="prev-button">&#10094; Previous</button>
                        <button onClick={handleNext} className="next-button">Next &#10095;</button>
                    </div>
                </div>
            </section>
            <footer>
                <FooterNavBar></FooterNavBar>
            </footer>
        </div>
    )
}

export default HomePage;