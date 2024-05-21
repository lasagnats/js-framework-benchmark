import "./App.css";
import { FrameworkType, knownIssues } from "./Common";
import ResultTable from "./components/ResultTable";
import SelectionBar from "./components/selection/SelectionBar";

const KnownIssuesList = () => {
  return (
    <section>
      <h3>Known issues and notes</h3>
      {knownIssues.map((issue) => (
        <dl key={issue.issue.toFixed()} id={issue.issue.toFixed()}>
          <dt>
            <a target="_blank" rel="noopener noreferrer" href={issue.link}>
              {issue.issue.toFixed()}
            </a>
          </dt>
          <dd>{issue.text}</dd>
        </dl>
      ))}
    </section>
  );
};

const App = () => {
  // eslint-disable-next-line no-constant-condition
  const disclaimer = false ? (
    <div>
      <h2>Results for js web frameworks benchmark - official run</h2>
      <p>
        A description of the benchmark and the source code and can be found in the github{" "}
        <a href="https://github.com/krausest/js-framework-benchmark">repository</a>.
      </p>
    </div>
  ) : (
    <p>
      Warning: These results are preliminary - use with caution (they may e.g. be from different browser versions).
      Official results are published on the{" "}
      <a href="https://krausest.github.io/js-framework-benchmark/index.html">results page</a>.
    </p>
  );

  const testEnvironmentInfo = (
    <p>
      The benchmark was run on a MacBook Pro 14 (32 GB RAM, 8/14 Cores, OSX 14.3.1), Chrome 122.0.6261.69 (arm64) using
      the puppeteer benchmark driver with reduced tracing.
    </p>
  );

  return (
    <>
      {disclaimer}
      {testEnvironmentInfo}
      <p>
        After chrome 119 official results we&apos;ve changed a detail for the benchmark: 
        We now open a new tab for each benchmark iteration, earlier runs reused the tab per benchmark and implementation. 
      </p>
      <p>
        Starting with chrome 118 the benchmark uses a <a href="https://github.com/krausest/js-framework-benchmark/wiki/Computation-of-the-weighted-geometric-mean">weighted geometric mean </a> to compute the overall result.
      </p>

      <main>
        <SelectionBar showDurationSelection={true} />
        <ResultTable type={FrameworkType.KEYED} />
        <ResultTable type={FrameworkType.NON_KEYED} />
        <KnownIssuesList></KnownIssuesList>
      </main>
    </>
  );
};

export default App;
