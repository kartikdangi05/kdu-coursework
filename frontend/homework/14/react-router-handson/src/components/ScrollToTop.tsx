import { useRef } from 'react';

const ScrollToTop = () => {
  const divRef = useRef<HTMLDivElement>(null);

  const scrollToDiv = () => {
    if (divRef.current) {
      divRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  return (
    <div>
      <div ref={divRef}>
        Scroll to This Div
      </div>
      <article style={{fontSize: '50px'}}>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Fugiat laudantium deleniti doloribus possimus sed non quam iure quaerat quas, ullam ipsam tempora pariatur qui id. Incidunt ea id ratione expedita.

      </article>
      <button onClick={scrollToDiv}>Scroll To Div</button>
    </div>
  );
}

export default ScrollToTop;
